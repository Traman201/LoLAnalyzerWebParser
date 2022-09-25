package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.BuildingKill;
import com.lolanalyzer.parcer.riotapi.ParticipantFramesAPI;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuildingKillAPI extends AbstractEventAPI{

    @Override
    public BuildingKill parseEvent(JSONObject o, Frame parentFrame) {
        BuildingKill buildingKill = (BuildingKill) super.parseEvent(o, parentFrame);

        ArrayList<Long> participants = new ArrayList<>();

        for(Object participant : o.getJSONArray("assistingParticipantIds")){
            participants.add((Long) participant);
        }
        buildingKill.setAssistingParticipantIds(participants);

        buildingKill.setBounty(o.getLong("bounty"));
        buildingKill.setBuildingType(o.getString("buildingType"));
        buildingKill.setKillerId(o.getLong("killerId"));
        buildingKill.setLaneType(o.getString("laneType"));
        buildingKill.setPosition(ParticipantFramesAPI.parsePosition(o.getJSONObject("position")));
        buildingKill.setTeamId(o.getLong("teamId"));
        buildingKill.setTowerType(o.getString("towerType"));

        return buildingKill;
    }
}
