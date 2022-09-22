package com.lolanalyzer.parcer.entity;

import com.lolanalyzer.parcer.riotapi.MatchAPI;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Setter
@Getter
@Slf4j
public class Match {

    @EmbeddedId
    MatchId id;


    @ElementCollection
    Map<String, String> textMatchData;
    @ElementCollection
    Map<String, Long> numericMatchData;

    public Match(){
        textMatchData = new HashMap<>();
        numericMatchData = new HashMap<>();



    }




}
