package com.lolanalyzer.parcer.entity;

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
public class Challenges implements Serializable {
    @ElementCollection
    Map<String, Long> longValues;
    @ElementCollection
    Map<String, Double> realValues;

    public Challenges(){
        longValues = new HashMap<>();
        realValues = new HashMap<>();
    }

    
}
