package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.EliteMonsterKill;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.riotapi.ParticipantFramesAPI;
import org.json.JSONException;
import org.json.JSONObject;

public class EliteMonsterKillAPI extends AbstractEventAPI{

    @Override
    public EliteMonsterKill parseEvent(JSONObject o, Frame parentFrame) {
        EliteMonsterKill eliteMonsterKill = new EliteMonsterKill();
        eliteMonsterKill.setId(getEventId(o, parentFrame));

        eliteMonsterKill.setBounty(o.getLong("bounty"));
        eliteMonsterKill.setKillerId(o.getLong("killerId"));
        eliteMonsterKill.setKillerTeamId(o.getLong("killerTeamId"));
        try {
            eliteMonsterKill.setMonsterSubType(o.getString("monsterSubType"));
        }catch (JSONException e){
            eliteMonsterKill.setMonsterSubType("None");
        }

        eliteMonsterKill.setMonsterType(o.getString("monsterType"));
        eliteMonsterKill.setPosition(ParticipantFramesAPI.parsePosition(o.getJSONObject("position")));

        return eliteMonsterKill;
    }
}
