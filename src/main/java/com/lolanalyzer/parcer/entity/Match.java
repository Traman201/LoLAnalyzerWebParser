package com.lolanalyzer.parcer.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@Slf4j
public class Match {

    @EmbeddedId
    MatchId id;

    long gameCreation, gameDuration, mapId, queueId;
    long gameEndTimestamp, gameStartTimestamp;
    String gameMode, gameName, gameType, gameVersion, tournamentCode, matchId;
    String dataVersion;



}
