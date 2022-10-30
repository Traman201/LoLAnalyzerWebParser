package com.lolanalyzer.parcer.entity.datadragon;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

@Entity
@Slf4j
@Getter
@Setter
public class ChampionStats {

    @Id
    String id;

    String name;

    @ElementCollection(fetch = FetchType.EAGER)
    Map<String, Double> stats;

    public ChampionStats(){
        stats = new HashMap<>();
    }
}
