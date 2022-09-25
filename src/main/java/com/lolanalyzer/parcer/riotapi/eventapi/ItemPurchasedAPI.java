package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entity.events.ItemPurchased;
import org.json.JSONObject;

public class ItemPurchasedAPI extends AbstractEventAPI{

    @Override
    public ItemPurchased parseEvent(JSONObject o, Frame parentFrame) {
        ItemPurchased itemPurchased = new ItemPurchased();
        itemPurchased.setId(getEventId(o, parentFrame));

        itemPurchased.setItemId(o.getLong("itemId"));
        itemPurchased.setParticipantId(o.getLong("participantId"));
        return itemPurchased;
    }
}
