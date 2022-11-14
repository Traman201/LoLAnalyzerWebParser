package com.lolanalyzer.parcer.embeddedparams;

import com.lolanalyzer.parcer.riotapi.ChallengesAPI;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Сущность с параметрами испытаний участника матча
 */
@Embeddable
@Getter
@Setter
public class Challenges implements Serializable {
    /**
     * Целые значения, содержащиеся в объекте Challenges
     *
     * Список значений генерируется в соответствующем классе-обработчике
     *
     * @see ChallengesAPI
     * @see ChallengesAPI#getPossibleNumericValueKeys()
     *
     */
    @ElementCollection
    Map<String, Long> longValues;

    /**
     * Вещественные значения, содержащиеся в объекте Challenges
     *
     * Список значений генерируется в соответствующем классе-обработчике
     *
     * @see ChallengesAPI
     * @see ChallengesAPI#getPossibleRealValueKeys()
     */
    @ElementCollection
    Map<String, Double> realValues;

    public Challenges(){
        longValues = new HashMap<>();
        realValues = new HashMap<>();
    }

    
}
