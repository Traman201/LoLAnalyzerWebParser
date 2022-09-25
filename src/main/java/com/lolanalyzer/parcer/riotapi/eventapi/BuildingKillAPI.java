package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.BuildingKill;
import org.json.JSONObject;

public class BuildingKillAPI extends AbstractEventAPI{

    @Override
    public BuildingKill parseEvent(JSONObject o, Frame parentFrame) {
        return null;
    }
}
