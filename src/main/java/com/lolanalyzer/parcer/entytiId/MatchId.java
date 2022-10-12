package com.lolanalyzer.parcer.entytiId;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Slf4j
@Embeddable
public class MatchId implements Serializable {
    private long gameId;
    private String platformId;
}
