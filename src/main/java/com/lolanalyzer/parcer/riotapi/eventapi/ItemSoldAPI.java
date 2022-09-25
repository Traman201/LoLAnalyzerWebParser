package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entity.events.ItemSold;
import com.lolanalyzer.parcer.entity.events.ItemUndo;
import org.json.JSONObject;

public class ItemSoldAPI extends AbstractEventAPI{

    @Override
    public ItemSold parseEvent(JSONObject o, Frame parentFrame) {
        ItemSold itemSold = (ItemSold) super.parseEvent(o, parentFrame);
        itemSold.setItemId(o.getLong("itemId"));
        itemSold.setParticipantId(o.getLong("participantId"));

        return itemSold;
    }
}
