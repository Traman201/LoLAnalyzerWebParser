package com.lolanalyzer.parcer.riotapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.ParticipantFrame;
import com.lolanalyzer.parcer.entity.Timeline;
import com.lolanalyzer.parcer.entytiId.FrameId;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FramesAPI {
    public static List<Frame> getFramesFromJSONArray(JSONArray framesJSONArray, Timeline timeline) {
        ArrayList<Frame> frames = new ArrayList<>();

        for (Object frameJSON : framesJSONArray) {
            frames.add(FramesAPI.parseFrame((JSONObject) frameJSON, timeline));
        }
        return frames;
    }

    private static Frame parseFrame(JSONObject frameJSON, Timeline timeline) {
        Frame frame = new Frame();

        FrameId id = new FrameId();
        id.setId(timeline.getId());
        id.setTimestamp(frameJSON.getLong("timestamp"));
        frame.setId(id);

        ArrayList<ParticipantFrame> participantFrames = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            participantFrames.add(ParticipantFramesAPI.parseParticipantFrame(
                            frameJSON.getJSONObject("participantFrames").
                                    getJSONObject(Integer.toString(i)),
                            frame
                    )
            );
        }
        frame.setParticipantFrames(participantFrames);

        return frame;
    }
}
