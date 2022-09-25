package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entity.events.ObjectiveBountyPrestart;
import org.json.JSONObject;

public class ObjectiveBountyPrestartAPI extends AbstractEventAPI{

    @Override
    public ObjectiveBountyPrestart parseEvent(JSONObject o, Frame parentFrame) {
        ObjectiveBountyPrestart bountyPrestart = (ObjectiveBountyPrestart) super.parseEvent(o, parentFrame);

        bountyPrestart.setActualStartTime(o.getLong("actualStartTime"));
        bountyPrestart.setTeamId(o.getLong("teamId"));

        return bountyPrestart;
    }
}
