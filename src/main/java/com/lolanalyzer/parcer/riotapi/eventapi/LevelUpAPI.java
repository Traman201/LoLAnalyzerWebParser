package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entity.events.LevelUp;
import org.json.JSONObject;

public class LevelUpAPI extends AbstractEventAPI{

    @Override
    public LevelUp parseEvent(JSONObject o, Frame parentFrame) {
        LevelUp levelUp = (LevelUp) super.parseEvent(o, parentFrame);

        levelUp.setLevel(o.getLong("level"));
        levelUp.setParticipantId(o.getLong("4"));

        return levelUp;
    }
}
