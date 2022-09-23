package com.lolanalyzer.parcer.entity;

import com.lolanalyzer.parcer.entytiId.MatchId;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Setter
@Getter
@Slf4j
public class Match {

    @EmbeddedId
    MatchId id;


    @ElementCollection
    Map<String, String> textData;
    @ElementCollection
    Map<String, Long> numericData;

    @OneToMany
    List<Participant> participants;

    @OneToOne
    Timeline timeline;

    public Match(){
        textData = new HashMap<>();
        numericData = new HashMap<>();
        participants = new ArrayList<>();
    }




}
