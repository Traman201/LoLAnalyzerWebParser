package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.BuildingKill;
import com.lolanalyzer.parcer.riotapi.ParticipantFramesAPI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuildingKillAPI extends AbstractEventAPI{

    @Override
    public BuildingKill parseEvent(JSONObject o, Frame parentFrame) {
        BuildingKill buildingKill = new BuildingKill();
        buildingKill.setId(getEventId(o, parentFrame));

        ArrayList<Long> participants = new ArrayList<>();

        try {
            for(Object participant : o.getJSONArray("assistingParticipantIds")){
                participants.add(Integer.toUnsignedLong((Integer) participant));
            }
        }catch (JSONException ignored){
        }


        buildingKill.setAssistingParticipantIds(participants);

        buildingKill.setBounty(o.getLong("bounty"));

        buildingKill.setBuildingType(o.getString("buildingType"));
        buildingKill.setKillerId(o.getLong("killerId"));
        buildingKill.setLaneType(o.getString("laneType"));
        buildingKill.setPosition(ParticipantFramesAPI.parsePosition(o.getJSONObject("position")));
        buildingKill.setTeamId(o.getLong("teamId"));

        try{
            buildingKill.setTowerType(o.getString("towerType"));
        }catch (JSONException e){
            buildingKill.setTowerType("None");
        }
        return buildingKill;
    }
}
