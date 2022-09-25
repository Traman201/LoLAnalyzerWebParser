package com.lolanalyzer.parcer.riotapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.*;
import com.lolanalyzer.parcer.riotapi.eventapi.*;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EventAPI {


    public static Event parseEvent(JSONObject o, Frame frame) {

        String type = o.getString("type");

        return EventAPIMap.getInstance().getEventAPIMap().get(type).parseEvent(o, frame);

    }



}
