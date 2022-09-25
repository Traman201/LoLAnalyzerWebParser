package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entity.events.ItemUndo;
import org.json.JSONObject;

public class ItemUndoAPI extends AbstractEventAPI{

    @Override
    public ItemUndo parseEvent(JSONObject o, Frame parentFrame) {
        return null;
    }
}
