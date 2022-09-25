package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.ChampionKill;
import com.lolanalyzer.parcer.entity.events.ChampionSpecialKill;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.riotapi.ParticipantFramesAPI;
import org.json.JSONObject;

public class ChampionSpecialKillAPI extends AbstractEventAPI{

    @Override
    public ChampionSpecialKill parseEvent(JSONObject o, Frame parentFrame) {
        ChampionSpecialKill championSpecialKill = (ChampionSpecialKill) super.parseEvent(o, parentFrame);

        championSpecialKill.setKillType(o.getString("killType"));
        championSpecialKill.setKillerId(o.getLong("killerId"));
        championSpecialKill.setMultiKillLength(o.getLong("multiKillLength"));
        championSpecialKill.setPosition(ParticipantFramesAPI.parsePosition(o.getJSONObject("position")));

        return championSpecialKill;
    }
}
