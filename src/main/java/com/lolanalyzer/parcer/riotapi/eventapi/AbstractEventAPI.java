package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entytiId.EventId;
import org.json.JSONObject;
import com.lolanalyzer.parcer.entity.Frame;

public class AbstractEventAPI {

    public Event parseEvent(JSONObject o, Frame parentFrame){
        Event event = new Event();
        EventId id = new EventId();
        id.setFrameId(parentFrame.getId());
        id.setPreciseTimestamp(o.getLong("timestamp"));

        event.setId(id);
        return event;
    }
    public EventId getEventId(JSONObject o, Frame parentFrame){
        EventId id = new EventId();
        id.setFrameId(parentFrame.getId());
        id.setPreciseTimestamp(o.getLong("timestamp"));
        return id;
    }
}
