package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.ChampionKill;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.riotapi.ParticipantFramesAPI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChampionKillAPI extends AbstractEventAPI{

    @Override
    public ChampionKill parseEvent(JSONObject o, Frame parentFrame) {
        ChampionKill championKill = new ChampionKill();
        championKill.setId(getEventId(o, parentFrame));

        championKill.setBounty(o.getLong("bounty"));
        championKill.setKillerId(o.getLong("killerId"));
        championKill.setShutdownBounty(o.getLong("shutdownBounty"));
        championKill.setVictimId(o.getLong("victimId"));
        championKill.setKillerStreakLength(o.getLong("killStreakLength"));

        ArrayList<Long> participantsIds = new ArrayList<>();
        try {
            for(Object participant : o.getJSONArray("assistingParticipantIds")){
                participantsIds.add(Integer.toUnsignedLong((Integer) participant));
            }
        }catch (JSONException ignored){}

        championKill.setAssistingParticipantIds(participantsIds);

        championKill.setPosition(ParticipantFramesAPI.parsePosition(o.getJSONObject("position")));

        return championKill;
    }
}
