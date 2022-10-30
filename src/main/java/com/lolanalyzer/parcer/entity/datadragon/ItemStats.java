package com.lolanalyzer.parcer.entity.datadragon;

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
@Table(name = "item")
public class ItemStats {

    @Id
    long id;

    String name;

    long totalGold;

    @ElementCollection(fetch = FetchType.EAGER)
    Map<String, Double> stats;

    public ItemStats(){
        stats = new HashMap<>();
    }
}
