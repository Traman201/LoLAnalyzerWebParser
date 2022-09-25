package com.lolanalyzer.parcer.entity.events;

import com.lolanalyzer.parcer.embeddedparams.ChampionStats;
import com.lolanalyzer.parcer.embeddedparams.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Setter
@Getter
@Slf4j
public class ChampionKill extends Event{

    @ElementCollection
    List<Long> assistingParticipantIds;

    long bounty;
    long killerStreakLength;
    long killerId;
    long shutdownBounty;
    long victimId;

    @Embedded
    Position position;


}
