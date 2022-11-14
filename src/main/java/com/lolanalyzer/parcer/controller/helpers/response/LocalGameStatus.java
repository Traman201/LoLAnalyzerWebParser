package com.lolanalyzer.parcer.controller.helpers.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Параметры текущей локальной игры
 */

@Setter
@Getter
public class LocalGameStatus {
    double kill;
    double abilityPower;
    double armor;
    double attackDamage;
    double attackSpeed;
    double healthMax;
    double lifesteal;
    double magicResist;
    double movementSpeed;
    double powerMax;

    String summonerName;
    String championName;
    String winChance;

    boolean isWorking;
}
