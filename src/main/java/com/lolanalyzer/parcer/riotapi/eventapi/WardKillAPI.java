package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entity.events.WardKill;
import com.lolanalyzer.parcer.entity.events.WardPlaced;
import org.json.JSONObject;

public class WardKillAPI extends AbstractEventAPI {

    @Override
    public WardKill parseEvent(JSONObject o, Frame parentFrame) {
        return null;
    }
}
