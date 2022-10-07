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
public class PerkStyleSelection implements Serializable {
    @ElementCollection
    Map<String, Long> longValues;

    public PerkStyleSelection(){
        longValues = new HashMap<>();
    }

    
}
