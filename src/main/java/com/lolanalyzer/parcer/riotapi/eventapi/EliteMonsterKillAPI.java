package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.EliteMonsterKill;
import com.lolanalyzer.parcer.entity.events.Event;
import org.json.JSONObject;

public class EliteMonsterKillAPI extends AbstractEventAPI{

    @Override
    public EliteMonsterKill parseEvent(JSONObject o, Frame parentFrame) {
        return null;
    }
}
