package com.lolanalyzer.parcer.datagather.crawler;

import com.lolanalyzer.parcer.entity.Match;
import com.lolanalyzer.parcer.entity.Participant;
import com.lolanalyzer.parcer.riotapi.MatchAPI;
import com.lolanalyzer.parcer.riotapi.PlayerAPI;
import com.lolanalyzer.parcer.service.DataSaver;
import com.lolanalyzer.parcer.service.MatchRepositoryManager;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

@Getter
@Setter
@Slf4j
@Service
public class Crawler {
    @Autowired
    MatchRepositoryManager manager;

    @Autowired
    DataSaver saver;

    String status;

    private long datasetsCounter = 0;
    private long remainingCount = 0;
    private long playerPool = 0;

    private boolean isCrawling = false;
    private boolean isRequestStop = false;

    public ArrayList<Match> getLastMatches(String puuid){
        status = "Retrieving recent matches from " + puuid;
        JSONArray o = null;
        String response = "";
        try {
            response = PlayerAPI.getLastMatches(puuid);
            if(response.matches("429")){
                log.info("Too many requests. Waiting");
                waitForReason(125000, "Too many requests.");
                response = PlayerAPI.getLastMatches(puuid);
            }
            o = new JSONArray(response);
        } catch (IOException | NullPointerException | JSONException e) {
            status = "Failed to retrieve last matches";
            log.error("Failed to retrieve last matches from " + puuid);
            log.info(response);
            return new ArrayList<>();
        }
        ArrayList<Match> matches = new ArrayList<>();
        for(Object matchId : o){
            try {
                status = "Parsing recent matches from " + puuid;
                matches.add(MatchAPI.getMatch((String) matchId));
            } catch (IOException e) {
                continue;
            }
        }
        return matches;
    }
    private void waitForReason(long millis, String reason){
        for(long i = 0; i < millis; i += 1000){
            status = reason + " Waiting. Time remaining: " + (millis - i) / 1000.0;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void fail(String message){
        log.error("Error. " + message);
        status = "Error. Stopping now. Error message: " + message;
        setRequestStop(true);
    }

    @Async
    public void crawling(String startName, long goal) {
        if(isCrawling){
            return;
        }
        status = "Initializing crawling";
        isCrawling = true;
        remainingCount = goal;
        ArrayDeque<String> puuids = new ArrayDeque<>();
        puuids.add(startName);

        mainloop:
        do{
            playerPool = puuids.size();
            if(puuids.isEmpty()){
                fail("Out of players");
                break;
            }
            for(Match m : getLastMatches(puuids.pop())){

                if(manager.hasMatch(m)){
                    log.info("Found existent match " + m.getId().getGameId());
                    continue;
                }
                for(Participant p : m.getParticipants()){
                    if(!puuids.contains(p.getId().getPuuid())){
                        puuids.add(p.getId().getPuuid());
                    }

                }
                if(!m.getTextData().get("gameMode").equals("CLASSIC")){
                    log.info("This match is not in classic mode " + m.getId().getGameId() +
                            " It is in " + m.getTextData().get("gameMode") + " instead");
                    continue;
                }



                while(saver.isFull()){
                    status = "Waiting for some free queue space";
                    waitForReason(80000, "Queue is full.");
                }
                saver.saveMatch(m);
                datasetsCounter++;
                remainingCount = goal - datasetsCounter;
                log.info("Added match " + m.getId().getGameId());
                status = "Saving matches";
                if(datasetsCounter >= goal){
                    status = "Crawling completed";
                    break mainloop;
                }
            }

        }while (!isRequestStop);

        log.info("Added " + datasetsCounter + " matches");

        datasetsCounter = 0;
        isRequestStop = false;
        isCrawling = false;
    }



}
