package com.lolanalyzer.parcer.entytiId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Slf4j
@Setter
@Getter
@Embeddable
public class EventId implements Serializable {
    long preciseTimestamp;
    FrameId frameId;
}
