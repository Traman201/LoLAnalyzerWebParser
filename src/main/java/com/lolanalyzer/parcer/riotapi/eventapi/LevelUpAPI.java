package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entity.events.LevelUp;
import org.json.JSONObject;

public class LevelUpAPI extends AbstractEventAPI{

    @Override
    public LevelUp parseEvent(JSONObject o, Frame parentFrame) {
        LevelUp levelUp = new LevelUp();
        levelUp.setId(getEventId(o, parentFrame));

        levelUp.setLevel(o.getLong("level"));
        levelUp.setParticipantId(o.getLong("participantId"));

        return levelUp;
    }
}
