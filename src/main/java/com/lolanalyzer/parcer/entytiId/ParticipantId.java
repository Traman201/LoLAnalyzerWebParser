package com.lolanalyzer.parcer.entytiId;

import com.lolanalyzer.parcer.entytiId.MatchId;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Slf4j
@Embeddable
public class ParticipantId implements Serializable {
    private String puuid;
    private MatchId matchId;

}
