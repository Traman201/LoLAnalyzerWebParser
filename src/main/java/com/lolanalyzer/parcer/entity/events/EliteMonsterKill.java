package com.lolanalyzer.parcer.entity.events;

import com.lolanalyzer.parcer.embeddedparams.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
@Slf4j
public class EliteMonsterKill extends Event{
    long bounty;
    long killerId;
    long killerTeamId;

    String monsterSubType;
    String monsterType;

    @Embedded
    Position position;
}
