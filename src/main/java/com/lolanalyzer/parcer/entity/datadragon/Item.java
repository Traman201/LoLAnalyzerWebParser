package com.lolanalyzer.parcer.entity.datadragon;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

@Entity
@Slf4j
@Getter
@Setter
public class Item {

    @Id
    long id;

    String name;

    long totalGold;

    @ElementCollection
    Map<String, Long> stats;

    public Item(){
        stats = new HashMap<>();
    }
}
