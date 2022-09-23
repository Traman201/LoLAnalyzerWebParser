package com.lolanalyzer.parcer.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Slf4j
@Getter
@Setter
public class Participant {
    @EmbeddedId
    ParticipantId id;


    @Embedded
    Challenges challenges;

    @ElementCollection
    Map<String, String> textData;

    @ElementCollection
    Map<String, Long> numericData;

    @ElementCollection
    Map<String, Boolean> booleanData;

    public Participant(){
        textData = new HashMap<>();
        numericData = new HashMap<>();
        booleanData = new HashMap<>();
    }




}
