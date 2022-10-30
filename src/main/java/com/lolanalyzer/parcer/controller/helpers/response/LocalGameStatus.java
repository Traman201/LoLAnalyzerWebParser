package com.lolanalyzer.parcer.controller.helpers.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LocalGameStatus {
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

    boolean isWorking;
}
