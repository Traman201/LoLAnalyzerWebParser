package com.lolanalyzer.parcer.riotapi;

public class ParticipantFramesAPI {
    public static String[] getChampionStatsKeys(){
        return new String[] {
                "abilityHaste",
                "abilityPower",
                "armor",
                "armorPen",
                "armorPenPercent",
                "attackDamage",
                "attackSpeed",
                "bonusArmorPenPercent",
                "bonusMagicPenPercent",
                "ccReduction",
                "cooldownReduction",
                "health",
                "healthMax",
                "healthRegen",
                "lifesteal",
                "magicPen",
                "magicPenPercent",
                "magicResist",
                "movementSpeed",
                "omnivamp",
                "physicalVamp",
                "power",
                "powerMax",
                "powerRegen",
                "spellVamp"
        };
    }
    public static String[] getDamageStatsKeys(){
        return new String[]{
                "magicDamageDone",
                "magicDamageDoneToChampions",
                "magicDamageTaken",
                "physicalDamageDone",
                "physicalDamageDoneToChampions",
                "physicalDamageTaken",
                "totalDamageDone",
                "totalDamageDoneToChampions",
                "totalDamageTaken",
                "trueDamageDone",
                "trueDamageDoneToChampions",
                "trueDamageTaken"
        };
    }
    public static String[] getPositionKeys(){
        return new String[]{
                "x",
                "y"
        };
    }

    public static String[] getFrameValueKeys(){
        return new String[]{
                "currentGold",
                "goldPerSecond",
                "jungleMinionsKilled",
                "level",
                "minionsKilled",
                "timeEnemySpentControlled",
                "totalGold",
                "xp"
        };
    }
}
