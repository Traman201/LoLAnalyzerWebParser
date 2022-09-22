package com.lolanalyzer.parcer.riotapi;

import com.lolanalyzer.parcer.entity.Match;
import com.lolanalyzer.parcer.entity.MatchId;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Call;
import org.json.JSONObject;
import org.springframework.jmx.export.metadata.ManagedAttribute;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;

@Slf4j
@Service
public class MatchAPI {
    public static String[] getPossibleTextMatchValues(){
        return new String[]  {
                "gameMode",
                "gameName",
                "gameType",
                "gameVersion",
                "tournamentCode",
                "matchId",
                "dataVersion"
        };
    }
    public static String[] getPossibleNumericMatchValues(){
        return new String[] {
                "gameCreation",
                "gameDuration",
                "mapId",
                "queueId",
                "gameEndTimestamp",
                "gameStartTimestamp"

        };
    }

    public static String getRawMatchData(String matchId) throws IOException {
        URL url = new URL("https://" +
                RiotAPIHelper.regionConverter(RiotAPIConfiguration.getInstance().region) +
                ".api.riotgames.com/lol/match/v5/matches/" + matchId);

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
        }
    }
    public static Match getMatch(String matchId) throws IOException {
        String rawJSON = getRawMatchData(matchId);
        JSONObject root = new JSONObject(rawJSON);

        JSONObject metadata = root.getJSONObject("metadata");
        JSONObject info = root.getJSONObject("info");

        Match match = new Match();
/*
        match.setMatchId(metadata.getString("matchId"));
        match.setDataVersion(metadata.getString("dataVersion"));
*/
        MatchId id = new MatchId();
        id.setPlatformId(info.getString("platformId"));
        id.setGameId(info.getLong("gameId"));
        match.setId(id);
        for(String textValueKey : getPossibleTextMatchValues()){
            try{
                match.getTextMatchData().put(textValueKey, info.getString(textValueKey));
            }catch (Exception e){
                match.getTextMatchData().put(textValueKey, metadata.getString(textValueKey));
            }
        }

        for(String numericValueKey : getPossibleNumericMatchValues()){
            match.getNumericMatchData().put(numericValueKey, info.getLong(numericValueKey));
        }
/*
        match.setGameCreation(info.getLong("gameCreation"));
        match.setGameDuration(info.getLong("gameDuration"));
        match.setGameEndTimestamp(info.getLong("gameEndTimestamp"));
        match.setGameMode(info.getString("gameMode"));
        match.setGameName("gameName");
        match.setGameStartTimestamp(info.getLong("gameStartTimestamp"));
        match.setGameType(info.getString("gameType"));
        match.setGameVersion(info.getString("gameVersion"));
        match.setMapId(info.getLong("mapId"));
        match.setQueueId(info.getLong("queueId"));
        match.setTournamentCode(info.getString("tournamentCode"));
*/
        log.info(info.getJSONArray("participants").toString());

        return match;
    }
}
