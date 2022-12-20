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

/**
 * Паук для сбора датасетов
 *
 * <p>
 *     Используется для сбора датасетов и записи их в базу данных. Работает в фоновом режиме.
 * </p>
 */
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

    private long failedMatches = 0;

    private boolean isCrawling = false;
    private boolean isRequestStop = false;

    /**
     * Получение последних сыгранных матчей посредством запроса через Riot API
     * @param puuid ID запрашиваемого игрока, зависит от API-ключа
     * @return Массив с последними сыгранными матчами игрока
     * @see Match
     */
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
            } catch (JSONException e) {
                log.error("Failed to parse a match " +  (String) matchId + " " + e.getMessage());
                failedMatches++;
            } catch (IOException e){
                waitForReason(125000, "Failed to parse a match. Trying to wait.");
                log.error("Failed to parse a match " +  (String) matchId + " " + e.getMessage());
            }
        }
        return matches;
    }

    /**
     * Постановка на паузу работы паука
     * <p>
     *     Чаще всего вызывается в связи с ограничением на отправку запросов со стороны Riot Games
     * </p>
     * @param millis Суммарное время паузы
     * @param reason Описание причины для постановки на паузу
     */
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

    /**
     * Внештатное завершение работы паука
     *
     * @param message Сообщение, описывающее внештатное завершение работы паука. Содежит текст ошибки
     */
    private void fail(String message){
        log.error("Error. " + message);
        status = "Error. Stopping now. Error message: " + message;
        setRequestStop(true);
    }

    /**
     * Работа паука
     *
     * <p>
     *     Основной метод работы паука. Принцип работы основывается на обработке матчей
     *     игроков, находящихся в одном матче с текущим игроком.
     *
     * </p>
     * <p>
     *     Может завершаться с ошибкой, если начинать работу паука с одного и то же игрока подряд
     *     или с игроков, матчи которых уже были обработаны
     * </p>
     *
     * @param startName ID игрока, с которого начинается работа паука. Зависит от API-ключа
     * @param goal Конечное число обработанных матчей
     */
    @Async
    public void crawling(String startName, long goal) {
        if(isCrawling){
            return;
        }
        status = "Initializing crawling";
        isCrawling = true;
        remainingCount = goal;
        failedMatches = 0;
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
                    failedMatches++;
                    continue;
                }
                for(Participant p : m.getParticipants()){
                    if(!puuids.contains(p.getId().getPuuid())){
                        puuids.add(p.getId().getPuuid());
                    }

                }
                if(!m.getTextData().get("gameMode").equals("CLASSIC")){
                    log.info("This match is not classic mode " + m.getId().getGameId() +
                            " It is " + m.getTextData().get("gameMode") + " instead");
                    failedMatches++;
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
