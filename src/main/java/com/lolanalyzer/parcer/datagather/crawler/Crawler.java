package com.lolanalyzer.parcer.datagather.crawler;

import com.lolanalyzer.parcer.entity.Match;
import com.lolanalyzer.parcer.entity.Participant;
import com.lolanalyzer.parcer.riotapi.MatchAPI;
import com.lolanalyzer.parcer.riotapi.PlayerAPI;
import com.lolanalyzer.parcer.service.MatchRepositoryManager;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
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

    private long datasetsCounter = 0;
    private long remainingCount = 0;

    private boolean isCrawling = false;
    private boolean isRequestStop = false;

    public ArrayList<Match> getLastMatches(String puuid){
        JSONArray o = null;
        try {
            o = new JSONArray(PlayerAPI.getLastMatches(puuid));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Match> matches = new ArrayList<>();

        for(Object matchId : o){
            try {
                matches.add(MatchAPI.getMatch((String) matchId));
            } catch (IOException e) {
                continue;
            }
        }
        return matches;
    }

    @Async
    public void crawling(String startName, long goal) {
        if(isCrawling){
            return;
        }
        isCrawling = true;
        remainingCount = goal;
        ArrayDeque<String> puuids = new ArrayDeque<>();
        puuids.add(startName);
        mainloop:
        do{
            for(Match m : getLastMatches(puuids.pop())){

                for(Participant p : m.getParticipants()){
                    puuids.add(p.getId().getPuuid());
                }
                if(manager.saveMatch(m)){
                    datasetsCounter++;
                    remainingCount = goal - datasetsCounter;
                    log.info("Added match " + m.getId().getGameId());
                }

                if(datasetsCounter >= goal){
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
