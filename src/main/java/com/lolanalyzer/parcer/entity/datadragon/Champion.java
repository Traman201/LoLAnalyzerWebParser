package com.lolanalyzer.parcer.entity.datadragon;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

@Entity
@Slf4j
@Getter
@Setter
public class Champion {

    @Id
    String id;

    String name;

    @ElementCollection
    Map<String, Long> stats;

    public Champion(){
        stats = new HashMap<>();
    }
}
