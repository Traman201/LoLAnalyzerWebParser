package com.lolanalyzer.parcer.entity.events;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;

@Entity
@Slf4j
@Getter
@Setter
public class LevelUp extends Event{
    long level;
    long participantId;
}
