package com.lolanalyzer.parcer.entity;


import com.lolanalyzer.parcer.embeddedparams.ChampionStats;
import com.lolanalyzer.parcer.embeddedparams.DamageStats;
import com.lolanalyzer.parcer.embeddedparams.Position;
import com.lolanalyzer.parcer.entytiId.ParticipantFrameId;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Slf4j
@Setter
@Getter
public class ParticipantFrame {

    @EmbeddedId
    ParticipantFrameId id;

    @Embedded
    ChampionStats championStats;

    @Embedded
    DamageStats damageStats;

    @Embedded
    Position position;

    @ElementCollection
    Map<String, Long> frameValues;

    public ParticipantFrame(){
        frameValues = new HashMap<>();
    }
}
