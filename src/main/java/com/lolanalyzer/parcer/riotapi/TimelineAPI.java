package com.lolanalyzer.parcer.riotapi;

import com.lolanalyzer.parcer.entity.Timeline;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;

public class TimelineAPI {

    public static String getRawMatchTimeline(String matchId) throws IOException {
        URL url = new URL("https://" +
                RiotAPIHelper.regionConverter(RiotAPIConfiguration.getInstance().region) +
                ".api.riotgames.com/lol/match/v5/matches/" + matchId + "/timeline");

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
    public static Timeline getTimeline(String matchId) throws IOException {
        String rawJSON = getRawMatchTimeline(matchId);
        JSONObject root = new JSONObject(rawJSON);

        JSONObject info = root.getJSONObject("info");

        Timeline timeline = new Timeline();

        timeline.setId(MatchAPI.constructMatchId(matchId));

        timeline.setFrameInterval(info.getLong("frameInterval"));

        timeline.setFrames(FramesAPI.getFramesFromJSONArray(info.getJSONArray("frames"), timeline));

        return timeline;

    }

}
