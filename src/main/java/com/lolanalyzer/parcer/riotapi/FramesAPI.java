package com.lolanalyzer.parcer.riotapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.ParticipantFrame;
import com.lolanalyzer.parcer.entity.Timeline;
import com.lolanalyzer.parcer.entytiId.FrameId;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class FramesAPI {
    public static List<Frame> getFramesFromJSONArray(JSONArray framesJSONArray, Timeline timeline) {
        ArrayList<Frame> frames = new ArrayList<>();

        for (Object frameJSON : framesJSONArray){
            frames.add(FramesAPI.parseFrame((JSONObject)frameJSON, timeline));
        }
        return frames;
    }

    private static Frame parseFrame(JSONObject frameJSON, Timeline timeline) {
        Frame frame = new Frame();

        FrameId id = new FrameId();
        id.setId(timeline.getId());
        id.setTimestamp(frameJSON.getLong("timestamp"));

        ArrayList<ParticipantFrame> participantFrames = new ArrayList<>();

        for(int i = 1; i <= 10; i++){
            participantFrames.add(ParticipantFramesAPI.parseParticipantFrame(
                    frameJSON.getJSONObject(Integer.toString(i)), frame));
        }

        return frame;
    }
}
