package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entity.events.SkillLevelUp;
import org.json.JSONObject;

public class SkillLevelUpAPI extends AbstractEventAPI{

    @Override
    public SkillLevelUp parseEvent(JSONObject o, Frame parentFrame) {
        SkillLevelUp skillLevelUp = (SkillLevelUp) super.parseEvent(o, parentFrame);

        skillLevelUp.setLevelUpType(o.getString("levelUpType"));
        skillLevelUp.setParticipantId(o.getLong("participantId"));
        skillLevelUp.setSkillSlot(o.getLong("skillSlot"));

        return skillLevelUp;
    }
}
