package com.lolanalyzer.parcer.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Challenges {
    double AssistStreakCount, abilityUses, acesBefore15Minutes; //12AssistStreakCount
    double alliedJungleMonsterKills, baronTakedowns, blastConeOppositeOpponentCount;
    double bountyGold, buffsStolen, completeSupportQuestInTime;
    double controlWardsPlaced, damagePerMinute, damageTakenOnTeamPercentage;
    double dancedWithRiftHerald, deathsByEnemyChamps, dodgeSkillShotsSmallWindow;
    double doubleAces, dragonTakedowns, earlyLaningPhaseGoldExpAdvantage;
    double effectiveHealAndShielding, elderDragonKillsWithOpposingSoul, elderDragonMultikills;
    double enemyChampionImmobilizations, enemyJungleMonsterKills, epicMonsterKillsNearEnemyJungler;
    double epicMonsterKillsWithin30SecondsOfSpawn, epicMonsterSteals, epicMonsterStolenWithoutSmite;
    double flawlessAces, fullTeamTakedown, gameLength;
    double getTakedownsInAllLanesEarlyJungleAsLaner, goldPerMinute, hadAfkTeammate;
    double hadOpenNexus, immobilizeAndKillWithAlly, initialBuffCount;
    double initialCrabCount, jungleCsBefore10Minutes, junglerTakedownsNearDamagedEpicMonster;
    double kTurretsDestroyedBeforePlatesFall, kda, killAfterHiddenWithAlly;
    double killedChampTookFullTeamDamageSurvived, killingSprees, killsNearEnemyTurret;
    double killsOnOtherLanesEarlyJungleAsLaner, killsOnRecentlyHealedByAramPack, killsUnderOwnTurret;
    double killsWithHelpFromEpicMonster, knockEnemyIntoTeamAndKill, landSkillShotsEarlyGame;
    double laneMinionsFirst10Minutes, laningPhaseGoldExpAdvantage, legendaryCount;
    double lostAnInhibitor, maxCsAdvantageOnLaneOpponent, maxKillDeficit;
    double maxLevelLeadLaneOpponent, moreEnemyJungleThanOpponent, multiKillOneSpell;
    double multiTurretRiftHeraldCount, multikills, multikillsAfterAggressiveFlash;
    double mythicItemUsed, outerTurretExecutesBefore10Minutes, outnumberedKills;
    double outnumberedNexusKill, perfectDragonSoulsTaken, perfectGame;
    double pickKillWithAlly, playedChampSelectPosition, poroExplosions;

    
}
