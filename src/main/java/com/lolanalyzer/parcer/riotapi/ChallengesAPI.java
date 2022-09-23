package com.lolanalyzer.parcer.riotapi;

import com.lolanalyzer.parcer.entity.Challenges;
import org.json.JSONObject;

public class ChallengesAPI {

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
