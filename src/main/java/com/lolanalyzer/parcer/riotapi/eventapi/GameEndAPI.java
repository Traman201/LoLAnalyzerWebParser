package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entity.events.GameEnd;
import org.json.JSONObject;

public class GameEndAPI extends AbstractEventAPI{

    @Override
    public GameEnd parseEvent(JSONObject o, Frame parentFrame) {
        return null;
    }
}
