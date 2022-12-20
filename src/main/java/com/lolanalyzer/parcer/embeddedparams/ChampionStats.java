package com.lolanalyzer.parcer.embeddedparams;

import com.lolanalyzer.parcer.riotapi.ParticipantFramesAPI;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Характеристики чемпиона в рамках одного таймфрейма
 */
@Embeddable
@Getter
@Setter
public class ChampionStats implements Serializable {

    /**
     * Массив параметров характеристик чемпиона
     *
     * @see com.lolanalyzer.parcer.riotapi.ParticipantFramesAPI
     * @see ParticipantFramesAPI#getChampionStatsKeys()
     */
    @ElementCollection
    Map<String, Long> stats;

    public ChampionStats(){
        stats = new HashMap<>();
    }
}
