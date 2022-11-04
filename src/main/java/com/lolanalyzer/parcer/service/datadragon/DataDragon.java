package com.lolanalyzer.parcer.service.datadragon;

import com.lolanalyzer.parcer.repositiory.datadragon.ChampionRepository;
import com.lolanalyzer.parcer.repositiory.datadragon.ItemRepository;
import com.lolanalyzer.parcer.riotapi.ChallengesAPI;
import com.lolanalyzer.parcer.riotapi.RiotAPIConfiguration;
import com.lolanalyzer.parcer.riotapi.RiotAPIHelper;
import com.lolanalyzer.parcer.riotapi.datadragon.ChampionAPI;
import com.lolanalyzer.parcer.riotapi.datadragon.ItemAPI;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Component
public class DataDragon {

    @Autowired
    ChampionRepository championRepository;

    @Autowired
    ItemRepository itemRepository;


    @PostConstruct
    private void fillChampionData() throws IOException {
        URL url = new URL("http://ddragon.leagueoflegends.com/cdn/12.20.1/data/en_US/champion.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        StringBuilder response = new StringBuilder();
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {

            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        JSONObject root = new JSONObject(response.toString());

        JSONObject data = root.getJSONObject("data");

        for(int i = 0; i < data.names().length(); i++){
            championRepository.save(ChampionAPI.parseChampion
                    (data.getJSONObject(data.names().getString(i))));
        }
    }
    @PostConstruct
    private void fillItemData() throws IOException {
        URL url = new URL("http://ddragon.leagueoflegends.com/cdn/12.20.1/data/en_US/item.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        StringBuilder response = new StringBuilder();
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {

            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        JSONObject root = new JSONObject(response.toString());

        JSONObject data = root.getJSONObject("data");

        for(int i = 0; i < data.names().length(); i++){
            itemRepository.save(ItemAPI.parseItem(
                    data.getJSONObject(data.names().getString(i)),
                    data.names().getInt(i)
            ));
        }
    }

}
