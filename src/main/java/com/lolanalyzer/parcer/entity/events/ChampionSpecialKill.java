package com.lolanalyzer.parcer.entity.events;

import com.lolanalyzer.parcer.embeddedparams.Position;

import javax.persistence.Embedded;

public class ChampionSpecialKill extends Event{
    String killType;
    long killerId;
    long multiKillLength;

    @Embedded
    Position position;

}
