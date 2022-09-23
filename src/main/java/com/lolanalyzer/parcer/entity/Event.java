package com.lolanalyzer.parcer.entity;

import com.lolanalyzer.parcer.entytiId.EventId;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Slf4j
@Getter
@Setter
@Entity
public class Event {

    @EmbeddedId
    EventId id;

    /*TODO*/
}
