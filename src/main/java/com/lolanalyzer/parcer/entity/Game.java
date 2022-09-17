package com.lolanalyzer.parcer.entity;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Slf4j
public class Game {
    @Id
    @Column(name = "match_id", nullable = false)
    private String matchId;

    long gameCreation, gameDuration, mapId, queueId;
    Timestamp gameEndTimestamp, gameStartTimestamp;
    String gameMode, gameName, gameType, gameVersion, platform;



}
