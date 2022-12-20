package com.lolanalyzer.parcer.entytiId;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Первичный ключ таймфрема участника матча
 */
@Getter
@Embeddable
@Setter
@Slf4j
public class ParticipantFrameId implements Serializable {
    /**
     * Родительский фрейм
     */
    FrameId frameId;
    /**
     * Идентификатор описываемого участника
     */
    long participantId;


}
