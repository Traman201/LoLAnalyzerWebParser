package com.lolanalyzer.parcer.entytiId;

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

    long id;

    long preciseTimestamp;
    FrameId frameId;

    public EventId(){
        id = createID();
    }
    private static long idCounter = 0;

    public static synchronized long createID()
    {
        return idCounter++;
    }
}
