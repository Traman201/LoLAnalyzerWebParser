package com.lolanalyzer.parcer.entytiId;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@Getter
@Setter
@Slf4j
public class FrameId implements Serializable {
    MatchId id;
    long timestamp;
}
