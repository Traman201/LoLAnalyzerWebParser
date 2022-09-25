package com.lolanalyzer.parcer.entity.events;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;

@Entity
@Setter
@Getter
@Slf4j
public class ItemUndo extends Event{
    long afterId;
    long beforeId;
    long goldGain;
    long participantId;
}
