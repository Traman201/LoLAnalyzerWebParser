package com.lolanalyzer.parcer.embeddedparams;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Embeddable
@Getter
@Setter
public class PerkStyle implements Serializable {
    @ElementCollection
    Map<String, String> textData;
    @ElementCollection
    Map<String, Long> longValues;

    @Embedded
    List<PerkStyleSelection> selections;

    public PerkStyle(){
        textData = new HashMap<>();
        longValues = new HashMap<>();
        selections = new ArrayList<>();
    }

    
}
