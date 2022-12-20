package com.lolanalyzer.parcer.embeddedparams;

import com.lolanalyzer.parcer.riotapi.ParticipantFramesAPI;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Статистика по урону для отдельного чемпиона в рамках одного таймфрейма
 */
@Slf4j
@Setter
@Getter
@Embeddable
public class DamageStats implements Serializable {

    /**
     * Массив со значениями статистики по урону чемпиона
     *
     * @see com.lolanalyzer.parcer.riotapi.ParticipantFramesAPI
     * @see ParticipantFramesAPI#getDamageStatsKeys()
     *
     */
    @ElementCollection
    Map<String, Long> stats;

    public DamageStats(){
        stats = new HashMap<>();
    }
}
