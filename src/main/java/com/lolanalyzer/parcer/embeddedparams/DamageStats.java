package com.lolanalyzer.parcer.embeddedparams;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Setter
@Getter
@Embeddable
public class DamageStats implements Serializable {

    @ElementCollection
    Map<String, Long> stats;

    public DamageStats(){
        stats = new HashMap<>();
    }
}
