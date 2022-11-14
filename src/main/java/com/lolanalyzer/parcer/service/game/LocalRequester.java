package com.lolanalyzer.parcer.service.game;

import com.lolanalyzer.parcer.entity.datadragon.ChampionStats;
import com.lolanalyzer.parcer.entity.datadragon.ItemStats;
import com.lolanalyzer.parcer.repositiory.datadragon.ChampionRepository;
import com.lolanalyzer.parcer.repositiory.datadragon.ItemRepository;
import com.lolanalyzer.parcer.riotapi.RiotAPIConfiguration;
import com.lolanalyzer.parcer.riotapi.RiotAPIHelper;
import com.lolanalyzer.parcer.service.TeamDiffCalculator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

@Service
@EnableScheduling
@Getter
@Slf4j
public class LocalRequester {
    boolean gameStarted = false;

    @Autowired
    ChampionRepository championRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    TeamDiffCalculator teamDiffCalculator;

    @Scheduled(fixedDelay = 1000)
    private void waitForGame(){
        if(!gameStarted && requestGameData() != null){
            parseGameData();

        }


    }
    @Scheduled(fixedDelay = 1000)
    public void updateGameData(){
        if(!gameStarted){
            return;
        }
        String gameData = requestGameData();
        if(gameData == null){
            gameStarted = false;
            return;
        }
        JSONObject root = new JSONObject(gameData);
        JSONArray allPlayers = root.getJSONArray("allPlayers");


        for(Object objectJSON : allPlayers){
            JSONObject playerJSON = (JSONObject) objectJSON;
            String summonerName = playerJSON.getString("summonerName");
            Champion champion = teamDiffCalculator.findChampion(summonerName);

            champion.setLevel(playerJSON.getInt("level"));

            JSONArray itemsArray = playerJSON.getJSONArray("items");
            champion.setItems(parseItems(itemsArray));
        }

    }
    private ArrayList<ItemStats> parseItems(JSONArray itemsArray){
        ArrayList<ItemStats> itemStats = new ArrayList<>();
        for(Object o : itemsArray){
            JSONObject itemJSON = (JSONObject) o;
            Optional<ItemStats> stats = itemRepository.findById(itemJSON.getLong("itemID"));
            stats.ifPresent(itemStats::add);
        }

        return itemStats;
    }

    private void parseGameData(){
        String gameData = requestGameData();
        if(gameData == null){
            gameStarted = false;
            return;
        }
        JSONObject root = new JSONObject(gameData);
        JSONArray allPlayers = root.getJSONArray("allPlayers");

        ArrayList<Champion> orderChampions, chaosChampions;
        orderChampions = new ArrayList<>();
        chaosChampions = new ArrayList<>();
        for(Object objectJSON : allPlayers){
            JSONObject playerJSON = (JSONObject) objectJSON;
            String id = playerJSON.getString("rawChampionName").split("_")[3];
            Optional<ChampionStats> stats = championRepository.findById(id);
            String summonerName = playerJSON.getString("summonerName");
            String championName = playerJSON.getString("championName");
            Champion champion;
            if(stats.isPresent()){
                champion = new Champion(stats.get(), summonerName, championName);
            }
            else{
                throw new RuntimeException("Failed to initialize a game");
            }
            if(playerJSON.getString("team").equals("ORDER")){
                orderChampions.add(champion);
            }
            else{
                chaosChampions.add(champion);
            }
        }
        teamDiffCalculator.setTeams(new Team(orderChampions), new Team(chaosChampions));
        gameStarted = true;
    }


    public String requestGameData(){
        HttpURLConnection con;
        try{
            URL url = new URL("https://127.0.0.1:2999/liveclientdata/allgamedata");

            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
        }catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();


        }catch (Exception e) {
            return null;
        }
    }
}
