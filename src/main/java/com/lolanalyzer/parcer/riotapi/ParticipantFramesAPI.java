package com.lolanalyzer.parcer.riotapi;

import com.lolanalyzer.parcer.embeddedparams.ChampionStats;
import com.lolanalyzer.parcer.embeddedparams.DamageStats;
import com.lolanalyzer.parcer.embeddedparams.Position;
import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.ParticipantFrame;
import com.lolanalyzer.parcer.entytiId.ParticipantFrameId;
import org.json.JSONObject;

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


    public static ParticipantFrame parseParticipantFrame(JSONObject frameJSONObject, Frame frame) {
        ParticipantFrame participantFrame = new ParticipantFrame();

        ParticipantFrameId id = new ParticipantFrameId();
        id.setFrameId(frame.getId());
        id.setParticipantId(frameJSONObject.getLong("participantId"));
        participantFrame.setId(id);

        participantFrame.setChampionStats(
                parseChampionStats(frameJSONObject.getJSONObject("championStats"))
        );
        participantFrame.setDamageStats(
                parseDamageStats(frameJSONObject.getJSONObject("damageStats"))
        );
        participantFrame.setPosition(
                parsePosition(frameJSONObject.getJSONObject("position"))
        );

        for(String frameValueKey : getFrameValueKeys()){
            participantFrame.getFrameValues().put(frameValueKey,
                    frameJSONObject.getLong(frameValueKey));
        }
        return participantFrame;
    }

    private static Position parsePosition(JSONObject positionJSON) {
        Position position = new Position();

        position.setX(positionJSON.getLong(getPositionKeys()[0]));
        position.setY(positionJSON.getLong(getPositionKeys()[1]));

        return position;
    }

    private static DamageStats parseDamageStats(JSONObject damageStatsJSON) {
        DamageStats damageStats = new DamageStats();

        for (String key : getDamageStatsKeys()){
            damageStats.getStats().put(key, damageStatsJSON.getLong(key));
        }

        return damageStats;
    }

    private static ChampionStats parseChampionStats(JSONObject championStatsJSON) {
        ChampionStats championStats = new ChampionStats();

        for(String key : getChampionStatsKeys()){
            championStats.getStats().put(key, championStatsJSON.getLong(key));
        }
        return championStats;

    }
}
