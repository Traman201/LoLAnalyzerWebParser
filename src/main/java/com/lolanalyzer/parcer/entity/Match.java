package com.lolanalyzer.parcer.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@Slf4j
public class Match {
    @Id
    @Column(name = "match_id", nullable = false)
    private String matchId;

    long gameCreation, gameDuration, mapId, queueId;
    long gameEndTimestamp, gameStartTimestamp, gameId;
    String gameMode, gameName, gameType, gameVersion, platformId, tournamentCode;
    String dataVersion;



}
