package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entity.events.TurretPlateDestroyed;
import com.lolanalyzer.parcer.riotapi.ParticipantFramesAPI;
import org.json.JSONObject;

public class TurretPlateDestroyedAPI extends AbstractEventAPI{


    @Override
    public TurretPlateDestroyed parseEvent(JSONObject o, Frame parentFrame) {
        TurretPlateDestroyed turretPlateDestroyed = new TurretPlateDestroyed();
        turretPlateDestroyed.setId(getEventId(o, parentFrame));

        turretPlateDestroyed.setKillerId(o.getLong("killerId"));
        turretPlateDestroyed.setLaneType(o.getString("laneType"));
        turretPlateDestroyed.setPosition(ParticipantFramesAPI.parsePosition(o.getJSONObject("position")));
        turretPlateDestroyed.setTeamId(o.getLong("teamId"));

        return turretPlateDestroyed;
    }
}
