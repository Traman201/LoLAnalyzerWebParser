package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entity.events.PauseEnd;
import com.lolanalyzer.parcer.entytiId.EventId;
import org.json.JSONObject;

public class PauseEndAPI extends AbstractEventAPI{

    @Override
    public PauseEnd parseEvent(JSONObject o, Frame parentFrame){
        PauseEnd event = new PauseEnd();
        event.setId(getEventId(o, parentFrame));

        event.setRealTimeStamp(o.getLong("realTimestamp"));
        return event;
    }
}
