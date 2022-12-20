package com.lolanalyzer.parcer.riotapi;

import com.lolanalyzer.parcer.embeddedparams.Challenges;
import org.json.JSONObject;

public class ChallengesAPI {

    /**
     * Возвращает строковый массив ключей для целых значений, хранящихся в объекте Challenges
     *
     * <p>
     *     Возвращает значения:
     * </p>
     *
     *
     * <ul>
     *     <li>12AssistStreakCount</li>
     *     <li>abilityUses</li>
     *     <li>acesBefore15Minutes</li>
     *     <li>alliedJungleMonsterKills</li>
     *     <li>baronTakedowns</li>
     *     <li>blastConeOppositeOpponentCount</li>
     *     <li>bountyGold</li>
     *     <li>buffsStolen</li>
     *     <li>completeSupportQuestInTime</li>
     *     <li>controlWardsPlaced</li>
     *     <li>dancedWithRiftHerald</li>
     *     <li>deathsByEnemyChamps</li>
     *     <li>dodgeSkillShotsSmallWindow</li>
     *     <li>doubleAces</li>
     *     <li>dragonTakedowns</li>
     *     <li>earlyLaningPhaseGoldExpAdvantage</li>
     *     <li>effectiveHealAndShielding</li>
     *     <li>elderDragonKillsWithOpposingSoul</li>
     *     <li>elderDragonMultikills</li>
     *     <li>enemyChampionImmobilizations</li>
     *     <li>enemyJungleMonsterKills</li>
     *     <li>epicMonsterKillsNearEnemyJungler</li>
     *     <li>epicMonsterKillsWithin30SecondsOfSpawn</li>
     *     <li>epicMonsterSteals</li>
     *     <li>epicMonsterStolenWithoutSmite</li>
     *     <li>flawlessAces</li>
     *     <li>fullTeamTakedown</li>
     *     <li>getTakedownsInAllLanesEarlyJungleAsLaner</li>
     *     <li>hadAfkTeammate</li>
     *     <li>hadOpenNexus</li>
     *     <li>immobilizeAndKillWithAlly</li>
     *     <li>initialBuffCount</li>
     *     <li>initialCrabCount</li>
     *     <li>jungleCsBefore10Minutes</li>
     *     <li>junglerTakedownsNearDamagedEpicMonster</li>
     *     <li>kTurretsDestroyedBeforePlatesFall</li>
     *     <li>kda</li>
     *     <li>killAfterHiddenWithAlly</li>
     *     <li>killedChampTookFullTeamDamageSurvived</li>
     *     <li>killingSprees</li>
     *     <li>killsNearEnemyTurret</li>
     *     <li>killsOnOtherLanesEarlyJungleAsLaner</li>
     *     <li>killsOnRecentlyHealedByAramPack</li>
     *     <li>killsUnderOwnTurret</li>
     *     <li>killsWithHelpFromEpicMonster</li>
     *     <li>knockEnemyIntoTeamAndKill</li>
     *     <li>landSkillShotsEarlyGame</li>
     *     <li>laneMinionsFirst10Minutes</li>
     *     <li>laningPhaseGoldExpAdvantage</li>
     *     <li>legendaryCount</li>
     *     <li>lostAnInhibitor</li>
     *     <li>maxKillDeficit</li>
     *     <li>maxLevelLeadLaneOpponent</li>
     *     <li>multiKillOneSpell</li>
     *     <li>multiTurretRiftHeraldCount</li>
     *     <li>multikills</li>
     *     <li>multikillsAfterAggressiveFlash</li>
     *     <li>mythicItemUsed</li>
     *     <li>outerTurretExecutesBefore10Minutes</li>
     *     <li>outnumberedKills</li>
     *     <li>outnumberedNexusKill</li>
     *     <li>perfectDragonSoulsTaken</li>
     *     <li>perfectGame</li>
     *     <li>pickKillWithAlly</li>
     *     <li>playedChampSelectPosition</li>
     *     <li>poroExplosions</li>
     *     <li>quickCleanse</li>
     *     <li>quickFirstTurret</li>
     *     <li>quickSoloKills</li>
     *     <li>riftHeraldTakedowns</li>
     *     <li>saveAllyFromDeath</li>
     *     <li>scuttleCrabKills</li>
     *     <li>skillshotsDodged</li>
     *     <li>skillshotsHit</li>
     *     <li>snowballsHit</li>
     *     <li>soloBaronKills</li>
     *     <li>soloKills</li>
     *     <li>stealthWardsPlaced</li>
     *     <li>survivedSingleDigitHpCount</li>
     *     <li>survivedThreeImmobilizesInFight</li>
     *     <li>takedownOnFirstTurret</li>
     *     <li>takedowns</li>
     *     <li>takedownsAfterGainingLevelAdvantage</li>
     *     <li>takedownsBeforeJungleMinionSpawn</li>
     *     <li>takedownsFirstXMinutes</li>
     *     <li>takedownsInAlcove</li>
     *     <li>takedownsInEnemyFountain</li>
     *     <li>teamBaronKills</li>
     *     <li>teamElderDragonKills</li>
     *     <li>teamRiftHeraldKills</li>
     *     <li>threeWardsOneSweeperCount</li>
     *     <li>tookLargeDamageSurvived</li>
     *     <li>turretPlatesTaken</li>
     *     <li>turretTakedowns</li>
     *     <li>turretsTakenWithRiftHerald</li>
     *     <li>twentyMinionsIn3SecondsCount</li>
     *     <li>unseenRecalls</li>
     * </ul>
     *
     * @return Массив со строковыми ключами
     */
    public static String[] getPossibleNumericValueKeys(){
        return new String[] {
                "12AssistStreakCount",
                "abilityUses",
                "acesBefore15Minutes",
                "alliedJungleMonsterKills",
                "baronTakedowns",
                "blastConeOppositeOpponentCount",
                "bountyGold",
                "buffsStolen",
                "completeSupportQuestInTime",
                "controlWardsPlaced",
                "dancedWithRiftHerald",
                "deathsByEnemyChamps",
                "dodgeSkillShotsSmallWindow",
                "doubleAces",
                "dragonTakedowns",
                "earlyLaningPhaseGoldExpAdvantage",
                "effectiveHealAndShielding",
                "elderDragonKillsWithOpposingSoul",
                "elderDragonMultikills",
                "enemyChampionImmobilizations",
                "enemyJungleMonsterKills",
                "epicMonsterKillsNearEnemyJungler",
                "epicMonsterKillsWithin30SecondsOfSpawn",
                "epicMonsterSteals",
                "epicMonsterStolenWithoutSmite",
                "flawlessAces",
                "fullTeamTakedown",
                "getTakedownsInAllLanesEarlyJungleAsLaner",
                "hadAfkTeammate",
                "hadOpenNexus",
                "immobilizeAndKillWithAlly",
                "initialBuffCount",
                "initialCrabCount",
                "jungleCsBefore10Minutes",
                "junglerTakedownsNearDamagedEpicMonster",
                "kTurretsDestroyedBeforePlatesFall",
                "kda",
                "killAfterHiddenWithAlly",
                "killedChampTookFullTeamDamageSurvived",
                "killingSprees",
                "killsNearEnemyTurret",
                "killsOnOtherLanesEarlyJungleAsLaner",
                "killsOnRecentlyHealedByAramPack",
                "killsUnderOwnTurret",
                "killsWithHelpFromEpicMonster",
                "knockEnemyIntoTeamAndKill",
                "landSkillShotsEarlyGame",
                "laneMinionsFirst10Minutes",
                "laningPhaseGoldExpAdvantage",
                "legendaryCount",
                "lostAnInhibitor",
                "maxKillDeficit",
                "maxLevelLeadLaneOpponent",
                "multiKillOneSpell",
                "multiTurretRiftHeraldCount",
                "multikills",
                "multikillsAfterAggressiveFlash",
                "mythicItemUsed",
                "outerTurretExecutesBefore10Minutes",
                "outnumberedKills",
                "outnumberedNexusKill",
                "perfectDragonSoulsTaken",
                "perfectGame",
                "pickKillWithAlly",
                "playedChampSelectPosition",
                "poroExplosions",
                "quickCleanse",
                "quickFirstTurret",
                "quickSoloKills",
                "riftHeraldTakedowns",
                "saveAllyFromDeath",
                "scuttleCrabKills",
                "skillshotsDodged",
                "skillshotsHit",
                "snowballsHit",
                "soloBaronKills",
                "soloKills",
                "stealthWardsPlaced",
                "survivedSingleDigitHpCount",
                "survivedThreeImmobilizesInFight",
                "takedownOnFirstTurret",
                "takedowns",
                "takedownsAfterGainingLevelAdvantage",
                "takedownsBeforeJungleMinionSpawn",
                "takedownsFirstXMinutes",
                "takedownsInAlcove",
                "takedownsInEnemyFountain",
                "teamBaronKills",
                "teamElderDragonKills",
                "teamRiftHeraldKills",
                "threeWardsOneSweeperCount",
                "tookLargeDamageSurvived",
                "turretPlatesTaken",
                "turretTakedowns",
                "turretsTakenWithRiftHerald",
                "twentyMinionsIn3SecondsCount",
                "unseenRecalls"

        };
    }

    /**
     * Возвращает строковый массив ключей для вещественных значений, хранящихся в объекте Challenges
     * 
     * <p>
     *     Возвращаемые значения
     * </p>
     * 
     * <ul>
     *     <li>damagePerMinute</li>
     *     <li>damageTakenOnTeamPercentage</li>
     *     <li>gameLength</li>
     *     <li>goldPerMinute</li>
     *     <li>maxCsAdvantageOnLaneOpponent</li>
     *     <li>moreEnemyJungleThanOpponent</li>
     *     <li>teamDamagePercentage</li>
     *     <li>visionScoreAdvantageLaneOpponent</li>
     *     <li>visionScorePerMinute</li>
     *     <li>wardTakedowns</li>
     *     <li>wardTakedownsBefore20M</li>
     *     <li>wardsGuarded</li>
     * </ul>
     * @return Массив со строковыми ключами
     */
    public static String[] getPossibleRealValueKeys(){
        return new String[]{
                "damagePerMinute",
                "damageTakenOnTeamPercentage",
                "gameLength",
                "goldPerMinute",
                "maxCsAdvantageOnLaneOpponent",
                "moreEnemyJungleThanOpponent",
                "teamDamagePercentage",
                "visionScoreAdvantageLaneOpponent",
                "visionScorePerMinute",
                "wardTakedowns",
                "wardTakedownsBefore20M",
                "wardsGuarded"


        };
    }

    /**
     * Возвращает объект Challenges из JSON-объекта
     * @param challengesJSON Объект challenges в виде JSON объекта
     * @return Объект Challenges
     */
    public static Challenges parseChallenges(JSONObject challengesJSON){
        Challenges challenges = new Challenges();
        for(String longKey : getPossibleNumericValueKeys()){
            try{
                challenges.getLongValues().put(longKey, challengesJSON.getLong(longKey));
            }catch (Exception e){
                challenges.getLongValues().put(longKey, null);
            }

        }
        for (String realKey : getPossibleRealValueKeys()){
            try{
                challenges.getRealValues().put(realKey, challengesJSON.getDouble(realKey));
            }catch (Exception e){
                challenges.getRealValues().put(realKey, null);
            }

        }

        return challenges;
    }
}
