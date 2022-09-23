package com.lolanalyzer.parcer.embeddedparams;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Embeddable
@Getter
@Setter
public class ChampionStats implements Serializable {

    @ElementCollection
    Map<String, Long> stats;

    public ChampionStats(){
        stats = new HashMap<>();
    }
}
