package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.ChampionKill;
import com.lolanalyzer.parcer.entity.events.Event;
import org.json.JSONObject;

public class ChampionKillAPI extends AbstractEventAPI{

    @Override
    public ChampionKill parseEvent(JSONObject o, Frame parentFrame) {
        return null;
    }
}
