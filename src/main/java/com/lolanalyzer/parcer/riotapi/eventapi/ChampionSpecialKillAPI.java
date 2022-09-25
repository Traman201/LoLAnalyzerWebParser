package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.ChampionKill;
import com.lolanalyzer.parcer.entity.events.ChampionSpecialKill;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.riotapi.ParticipantFramesAPI;
import org.json.JSONException;
import org.json.JSONObject;

public class ChampionSpecialKillAPI extends AbstractEventAPI{

    @Override
    public ChampionSpecialKill parseEvent(JSONObject o, Frame parentFrame) {
        ChampionSpecialKill championSpecialKill = new ChampionSpecialKill();
        championSpecialKill.setId(getEventId(o, parentFrame));

        championSpecialKill.setKillType(o.getString("killType"));
        championSpecialKill.setKillerId(o.getLong("killerId"));
        try{
            championSpecialKill.setMultiKillLength(o.getLong("multiKillLength"));
        }catch (JSONException e){
            championSpecialKill.setMultiKillLength(0);
        }
        championSpecialKill.setPosition(ParticipantFramesAPI.parsePosition(o.getJSONObject("position")));

        return championSpecialKill;
    }
}
