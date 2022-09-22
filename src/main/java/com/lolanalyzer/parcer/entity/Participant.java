package com.lolanalyzer.parcer.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Slf4j
@Getter
@Setter
public class Participant {
    @EmbeddedId
    ParticipantId id;

    long assists, baronKills, basicPings;
    long bountyLevel;


}
