package com.lolanalyzer.parcer.entity.events;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Slf4j
public class ItemPurchased extends Event{
    int itemId;
    int participantId;
}
