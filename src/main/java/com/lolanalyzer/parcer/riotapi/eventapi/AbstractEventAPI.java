package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.events.Event;
import org.json.JSONObject;
import com.lolanalyzer.parcer.entity.Frame;

public abstract class AbstractEventAPI {

    public abstract Event parseEvent(JSONObject o, Frame parentFrame);
}
