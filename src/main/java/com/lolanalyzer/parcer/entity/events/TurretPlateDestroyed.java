package com.lolanalyzer.parcer.entity.events;


import com.lolanalyzer.parcer.embeddedparams.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Slf4j
public class TurretPlateDestroyed extends Event{
    long killerId;
    long teamId;
    String laneType;

    @Embedded
    Position position;

}
