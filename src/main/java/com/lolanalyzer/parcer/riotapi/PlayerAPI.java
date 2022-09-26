package com.lolanalyzer.parcer.riotapi;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class PlayerAPI {

    public static String getLastMatches(String puuid) throws IOException {
        URL url = new URL("https://" +
                RiotAPIHelper.regionConverter(RiotAPIConfiguration.getInstance().region) +
                ".api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("X-Riot-Token", RiotAPIConfiguration.getInstance().getApiKey());
        con.setDoOutput(true);


        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();


        }catch (Exception e){
            return null;
        }

    }
}
