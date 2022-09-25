package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entity.events.TurretPlateDestroyed;
import org.json.JSONObject;

public class TurretPlateDestroyedAPI extends AbstractEventAPI{


    @Override
    public TurretPlateDestroyed parseEvent(JSONObject o, Frame parentFrame) {
        return null;
    }
}
