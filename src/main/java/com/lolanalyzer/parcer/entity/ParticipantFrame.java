package com.lolanalyzer.parcer.entity;


import com.lolanalyzer.parcer.embeddedparams.ChampionStats;
import com.lolanalyzer.parcer.embeddedparams.DamageStats;
import com.lolanalyzer.parcer.embeddedparams.Position;
import com.lolanalyzer.parcer.entytiId.ParticipantFrameId;
import com.lolanalyzer.parcer.riotapi.ParticipantFramesAPI;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Данные о чемпионе в рамках одного таймфрейма
 */
@Entity
@Slf4j
@Setter
@Getter
public class ParticipantFrame {

    /**
     * Первичный ключ таймфрейма
     *
     * @see ParticipantFrameId
     */
    @EmbeddedId
    ParticipantFrameId id;

    /**
     * Характеристики чемпиона
     *
     * @see ChampionStats
     * @see ParticipantFramesAPI#getChampionStatsKeys()
     */
    @Embedded
    ChampionStats championStats;

    /**
     * Статистика по урону
     *
     * @see DamageStats
     * @see ParticipantFramesAPI#getDamageStatsKeys()
     */
    @Embedded
    DamageStats damageStats;

    /**
     * Позиция игрока
     * @see Position
     */
    @Embedded
    Position position;

    /**
     * Общая информация об игроке в рамках таймфрейма
     *
     * @see ParticipantFramesAPI#getFrameValueKeys()
     */
    @ElementCollection
    Map<String, Long> frameValues;

    public ParticipantFrame(){
        frameValues = new HashMap<>();
    }
}
