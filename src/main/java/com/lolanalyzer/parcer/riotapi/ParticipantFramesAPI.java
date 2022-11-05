package com.lolanalyzer.parcer.riotapi;

import com.lolanalyzer.parcer.embeddedparams.ChampionStats;
import com.lolanalyzer.parcer.embeddedparams.DamageStats;
import com.lolanalyzer.parcer.embeddedparams.Position;
import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.ParticipantFrame;
import com.lolanalyzer.parcer.entytiId.ParticipantFrameId;
import org.json.JSONObject;

/**
 * Класс-обработчик для сущности ParticipantFrame
 */
public class ParticipantFramesAPI {
    /**
     * Возвращает строковый массив ключей для характеристик чемпиона в рамках одного таймфрейма
     * 
     * <p>
     *     Возвращаемые значения
     * </p>
     * 
     * <ul>
     *     <li>abilityHaste</li>
     *     <li>abilityPower</li>
     *     <li>armor</li>
     *     <li>armorPen</li>
     *     <li>armorPenPercent</li>
     *     <li>attackDamage</li>
     *     <li>attackSpeed</li>
     *     <li>bonusArmorPenPercent</li>
     *     <li>bonusMagicPenPercent</li>
     *     <li>ccReduction</li>
     *     <li>cooldownReduction</li>
     *     <li>health</li>
     *     <li>healthMax</li>
     *     <li>healthRegen</li>
     *     <li>lifesteal</li>
     *     <li>magicPen</li>
     *     <li>magicPenPercent</li>
     *     <li>magicResist</li>
     *     <li>movementSpeed</li>
     *     <li>omnivamp</li>
     *     <li>physicalVamp</li>
     *     <li>power</li>
     *     <li>powerMax</li>
     *     <li>powerRegen</li>
     *     <li>spellVamp</li>
     * </ul>
     * @return
     */
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

    /**
     * Возвращает строковый массив ключей для значений статистики по урону чемпиона в рамках одного таймфрейма
     * 
     * <p>
     *     Возвращаемые значения:
     * </p>
     * <ul>
     *      <li>magicDamageDone</li>
     *      <li>magicDamageDoneToChampions</li>
     *      <li>magicDamageTaken</li>
     *      <li>physicalDamageDone</li>
     *      <li>physicalDamageDoneToChampions</li>
     *      <li>physicalDamageTaken</li>
     *      <li>totalDamageDone</li>
     *      <li>totalDamageDoneToChampions</li>
     *      <li>totalDamageTaken</li>
     *      <li>trueDamageDone</li>
     *      <li>trueDamageDoneToChampions</li>
     *      <li>trueDamageTaken</li>
     * </ul>
     *
     * @return
     */
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

    /**
     * Возвращает строковый массив с ключами положения игрока
     * @return Массив с двумя значениям "x" и "y"
     */
    public static String[] getPositionKeys(){
        return new String[]{
                "x",
                "y"
        };
    }

    /**
     * Возвращает строковый массив с ключами для общей информации об игроке в рамках одного таймфрейма
     * <p>
     *     Возвращаемые значения:
     * </p>
     *
     * <ul>
     *     <li>currentGold</li>
     *     <li>goldPerSecond</li>
     *     <li>jungleMinionsKilled</li>
     *     <li>level</li>
     *     <li>minionsKilled</li>
     *     <li>timeEnemySpentControlled</li>
     *     <li>totalGold</li>
     *     <li>xp</li>
     * </ul>
     * @return
     */
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


    /**
     * Создает объект ParticipantFrame из соответствующего объекта JSON
     *
     * <p>
     *     Во время работы также создает соответствующие объекты со статистикой игрока
     *     и добавляет их к объекту фрейма
     * </p>
     *
     * @param frameJSONObject ParticipantFrame в виде JSON-объекта
     * @param frame Родительский таймфрейм
     * @return Объект ParticipantFrame
     * @see ParticipantFrame
     */
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

    /**
     * Создает объект Position из его представления в JSON-объекте
     * @param positionJSON JSON-представление объекта Position
     * @return Объект Position
     * @see Position
     */
    public static Position parsePosition(JSONObject positionJSON) {
        Position position = new Position();

        position.setX(positionJSON.getLong(getPositionKeys()[0]));
        position.setY(positionJSON.getLong(getPositionKeys()[1]));

        return position;
    }

    /**
     * Создает объект DamageStats из его представления в JSON-объекте
     * @param damageStatsJSON JSON-представление объекта DamageStats
     * @return Объект DamageStats
     * @see DamageStats
     */
    private static DamageStats parseDamageStats(JSONObject damageStatsJSON) {
        DamageStats damageStats = new DamageStats();

        for (String key : getDamageStatsKeys()){
            damageStats.getStats().put(key, damageStatsJSON.getLong(key));
        }

        return damageStats;
    }

    /**
     * Создает объект ChampionStats из его представлениия в JSON-объекте
     * @param championStatsJSON JSON-представление объекта ChampionStats
     * @return Объект ChampionStats
     * @see ChampionStats
     */

    private static ChampionStats parseChampionStats(JSONObject championStatsJSON) {
        ChampionStats championStats = new ChampionStats();

        for(String key : getChampionStatsKeys()){
            championStats.getStats().put(key, championStatsJSON.getLong(key));
        }
        return championStats;

    }
}
