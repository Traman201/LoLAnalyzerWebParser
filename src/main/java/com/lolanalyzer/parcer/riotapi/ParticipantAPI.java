package com.lolanalyzer.parcer.riotapi;

import com.lolanalyzer.parcer.entity.MatchId;
import com.lolanalyzer.parcer.entity.Participant;
import com.lolanalyzer.parcer.entity.ParticipantId;
import org.json.JSONObject;

public class ParticipantAPI {

    public static String[] getPossibleTextValueKeys(){
        return new String[]{
                "championName",
                "individualPosition",
                "lane",
                "riotIdName",
                "riotIdTagline",
                "role",
                "summonerId",
                "summonerName",
                "teamPosition",
        };
    }
    public static String[] getPossibleNumericValueKeys(){
        return new String[]{
                "assists",
                "baronKills",
                "basicPings",
                "bountyLevel",
                "champExperience",
                "champLevel",
                "championId",
                "championTransform",
                "consumablesPurchased",
                "damageDealtToBuildings",
                "damageDealtToObjectives",
                "damageDealtToTurrets",
                "damageSelfMitigated",
                "deaths",
                "detectorWardsPlaced",
                "doubleKills",
                "dragonKills",
                "goldEarned",
                "goldSpent",
                "inhibitorKills",
                "inhibitorTakedowns",
                "inhibitorsLost",
                "item0",
                "item1",
                "item2",
                "item3",
                "item4",
                "item5",
                "item6",
                "itemsPurchased",
                "killingSprees",
                "kills",
                "largestCriticalStrike",
                "largestKillingSpree",
                "largestMultiKill",
                "longestTimeSpentLiving",
                "magicDamageDealt",
                "magicDamageDealtToChampions",
                "magicDamageTaken",
                "neutralMinionsKilled",
                "nexusKills",
                "nexusLost",
                "nexusTakedowns",
                "objectivesStolen",
                "objectivesStolenAssists",
                "participantId",
                "pentaKills",
                "physicalDamageDealt",
                "physicalDamageDealtToChampions",
                "physicalDamageTaken",
                "profileIcon",
                "quadraKills",
                "sightWardsBoughtInGame",
                "spell1Casts",
                "spell2Casts",
                "spell3Casts",
                "spell4Casts",
                "summoner1Casts",
                "summoner1Id",
                "summoner2Casts",
                "summoner2Id",
                "summonerLevel",
                "teamId",
                "timeCCingOthers",
                "timePlayed",
                "totalDamageDealt",
                "totalDamageDealtToChampions",
                "totalDamageShieldedOnTeammates",
                "totalDamageTaken",
                "totalHeal",
                "totalHealsOnTeammates",
                "totalMinionsKilled",
                "totalTimeCCDealt",
                "totalTimeSpentDead",
                "totalUnitsHealed",
                "tripleKills",
                "trueDamageDealt",
                "trueDamageDealtToChampions",
                "trueDamageTaken",
                "turretKills",
                "turretTakedowns",
                "turretsLost",
                "unrealKills",
                "visionScore",
                "visionWardsBoughtInGame",
                "wardsKilled",
                "wardsPlaced"

        };
    }
    public static String[] getPossibleBooleanValueKeys(){
        return new String[]{
                "eligibleForProgression",
                "firstBloodAssist",
                "firstBloodKill",
                "firstTowerAssist",
                "firstTowerKill",
                "gameEndedInEarlySurrender",
                "gameEndedInSurrender",
                "teamEarlySurrendered",
                "win"
        };
    }



    public static Participant parseParticipant(JSONObject participantJSON, MatchId matchId){
        Participant participant = new Participant();

        ParticipantId pId = new ParticipantId();
        pId.setMatchId(matchId);
        pId.setPuuid(participantJSON.getString("puuid"));
        participant.setId(pId);

        for(String textKey : getPossibleTextValueKeys()){
            participant.getTextData().put(textKey, participantJSON.getString(textKey));
        }
        for(String numericKey : getPossibleNumericValueKeys()){
            participant.getNumericData().put(numericKey, participantJSON.getLong(numericKey));
        }
        for(String boolKey : getPossibleBooleanValueKeys()){
            participant.getBooleanData().put(boolKey, participantJSON.getBoolean(boolKey));
        }
        participant.setChallenges(ChallengesAPI.parseChallenges(participantJSON.getJSONObject("challenges")));
        return participant;
    }
}
