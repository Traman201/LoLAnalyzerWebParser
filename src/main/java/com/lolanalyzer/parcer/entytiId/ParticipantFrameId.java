package com.lolanalyzer.parcer.entytiId;

import com.lolanalyzer.parcer.entytiId.FrameId;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Getter
@Embeddable
@Setter
@Slf4j
public class ParticipantFrameId implements Serializable {
    FrameId frameId;
    long participantId;


}
