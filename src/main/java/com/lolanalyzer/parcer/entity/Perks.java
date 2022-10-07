package com.lolanalyzer.parcer.entity;

import com.lolanalyzer.parcer.embeddedparams.PerkStats;
import com.lolanalyzer.parcer.embeddedparams.PerkStyle;
import com.lolanalyzer.parcer.entity.Participant;
import com.lolanalyzer.parcer.entytiId.ParticipantId;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Perks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Embedded
    PerkStats statPerks;

    @Embedded
    PerkStyle primaryStyle;

    @Embedded
    PerkStyle subStyle;

    public Perks(){}

    
}
