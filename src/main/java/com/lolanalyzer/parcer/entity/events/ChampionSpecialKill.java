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
public class ChampionSpecialKill extends Event{
    String killType;
    long killerId;
    long multiKillLength;

    @Embedded
    Position position;

}
