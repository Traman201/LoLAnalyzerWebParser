package com.lolanalyzer.parcer.entity.events;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Slf4j
public class ItemSold extends Event{
    long itemId;
    long participantId;
}
