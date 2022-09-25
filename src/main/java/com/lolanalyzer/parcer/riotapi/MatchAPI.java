package com.lolanalyzer.parcer.riotapi;

import com.lolanalyzer.parcer.entity.Match;
import com.lolanalyzer.parcer.entytiId.MatchId;
import com.lolanalyzer.parcer.entity.Participant;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Slf4j
@Service
public class MatchAPI {


    public static String[] getPossibleTextValueKeys(){
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
    public static String[] getPossibleNumericValueKeys(){
        return new String[] {
                "gameCreation",
                "gameDuration",
                "mapId",
                "queueId",
                "gameEndTimestamp",
                "gameStartTimestamp"

        };
    }

    public static MatchId constructMatchId(String matchId){
        MatchId id = new MatchId();

        String[] splitted = matchId.split("_");
        id.setPlatformId(splitted[0]);
        id.setGameId(Long.parseLong(splitted[1]));
        return id;
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

        MatchId id = new MatchId();
        id.setPlatformId(info.getString("platformId"));
        id.setGameId(info.getLong("gameId"));
        match.setId(id);

        for(String textValueKey : getPossibleTextValueKeys()){
            try{
                match.getTextData().put(textValueKey, info.getString(textValueKey));
            }catch (Exception e){
                match.getTextData().put(textValueKey, metadata.getString(textValueKey));
            }
        }

        for(String numericValueKey : getPossibleNumericValueKeys()){
            match.getNumericData().put(numericValueKey, info.getLong(numericValueKey));
        }
        match.setParticipants(getParticipants(info.getJSONArray("participants"), id));
        match.setTimeline(TimelineAPI.getTimeline(matchId));
        return match;
    }
    public static ArrayList<Participant> getParticipants(JSONArray participantsJSON, MatchId matchId){
        ArrayList<Participant> participants = new ArrayList<>();
        for(Object participantJSON : participantsJSON){
            participants.add(ParticipantAPI.parseParticipant((JSONObject)participantJSON, matchId));
        }
        return participants;
    }

}
