package com.lolanalyzer.parcer.entity.events;

import com.lolanalyzer.parcer.entytiId.EventId;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Slf4j
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Event {

    @EmbeddedId
    EventId id;

}
