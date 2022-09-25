package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entity.events.ItemDestroyed;
import org.json.JSONObject;

public class ItemDestroyedAPI extends AbstractEventAPI{

    @Override
    public ItemDestroyed parseEvent(JSONObject o, Frame parentFrame) {
        return null;
    }
}
