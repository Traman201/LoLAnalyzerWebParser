package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entity.events.ItemUndo;
import org.json.JSONObject;

public class ItemUndoAPI extends AbstractEventAPI{

    @Override
    public ItemUndo parseEvent(JSONObject o, Frame parentFrame) {
        ItemUndo itemUndo = new ItemUndo();
        itemUndo.setId(getEventId(o, parentFrame));

        itemUndo.setAfterId(o.getLong("afterId"));
        itemUndo.setBeforeId(o.getLong("beforeId"));
        itemUndo.setGoldGain(o.getLong("goldGain"));
        itemUndo.setParticipantId(o.getLong("participantId"));

        return itemUndo;
    }
}
