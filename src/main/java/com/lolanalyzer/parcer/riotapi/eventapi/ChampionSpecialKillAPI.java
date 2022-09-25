package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.ChampionKill;
import com.lolanalyzer.parcer.entity.events.ChampionSpecialKill;
import com.lolanalyzer.parcer.entity.events.Event;
import org.json.JSONObject;

public class ChampionSpecialKillAPI extends AbstractEventAPI{

    @Override
    public ChampionSpecialKill parseEvent(JSONObject o, Frame parentFrame) {
        return null;
    }
}
