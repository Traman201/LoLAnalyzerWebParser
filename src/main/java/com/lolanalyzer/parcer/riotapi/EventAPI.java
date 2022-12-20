package com.lolanalyzer.parcer.riotapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.*;
import com.lolanalyzer.parcer.riotapi.eventapi.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EventAPI {


    public static Event parseEvent(JSONObject o, Frame frame) {

        String type = o.getString("type");
        return EventAPIMap.getInstance().getEventAPIMap().get(type).parseEvent(o, frame);

    }



}
