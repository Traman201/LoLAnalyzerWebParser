package com.lolanalyzer.parcer.entity.events;

import com.lolanalyzer.parcer.embeddedparams.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Slf4j
@Setter
@Getter
public class BuildingKill extends Event{

    @ElementCollection
    List<Long> assistingParticipantIds;

    long bounty;
    long killerId;
    long teamId;

    String laneType;
    String buildingType;
    String towerType;

    @Embedded
    Position position;

    public BuildingKill(){
        assistingParticipantIds = new ArrayList<>();
    }



}
