package com.lolanalyzer.parcer.riotapi;

import com.lolanalyzer.parcer.embeddedparams.*;
import com.lolanalyzer.parcer.entity.Perks;
import com.lolanalyzer.parcer.entytiId.MatchId;
import com.lolanalyzer.parcer.entity.Participant;
import com.lolanalyzer.parcer.entytiId.ParticipantId;
import org.apache.commons.logging.Log;
import org.json.JSONException;
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
                "description",
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
                "wardsPlaced",
                "defense",
                "flex",
                "offense",
                "style",
                "perk",
                "var1",
                "var2",
                "var3",

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
            try {
                participant.getTextData().put(textKey, participantJSON.getString(textKey));
            } catch (JSONException e) {};
        }
        for(String numericKey : getPossibleNumericValueKeys()){
            try {
                participant.getNumericData().put(numericKey, participantJSON.getLong(numericKey));
            } catch (JSONException e) {};

        }
        for(String boolKey : getPossibleBooleanValueKeys()){
            try {
                participant.getBooleanData().put(boolKey, participantJSON.getBoolean(boolKey));
            } catch (JSONException e) {};
        }
        try{
            participant.setChallenges(ChallengesAPI.parseChallenges(participantJSON.getJSONObject("challenges")));
        }catch (JSONException e){
            participant.setChallenges(new Challenges());
        }

        try{
            participant.setPerks(parsePerks(participantJSON.getJSONObject("perks")));
        }catch (JSONException e){
            participant.setPerks(new Perks());
        }

        return participant;
    }

    public static Perks parsePerks(JSONObject perksJSON){
        Perks perks = new Perks();
        PerkStats perkStats = new PerkStats();


        var perkStatsJSON = perksJSON.getJSONObject("statPerks");

        for(String longKey : getPossibleNumericValueKeys()) {
            try {
                perkStats.getLongValues().put(longKey, perkStatsJSON.getLong(longKey));
            } catch (Exception e) {
                perkStats.getLongValues().put(longKey, null);
            }
        }

        perks.setStatPerks(perkStats);

        var perkStyleJSON = perksJSON.getJSONArray("styles");
        for (var i = 0; i < perkStyleJSON.length(); i++) {
            PerkStyle perkStyle = new PerkStyle();

            for (String longKey : getPossibleNumericValueKeys()) {
                try {
                    perkStyle.getLongValues().put(longKey, perkStyleJSON.getJSONObject(i).getLong(longKey));
                } catch (Exception e) {
                    perkStyle.getLongValues().put(longKey, null);
                }
            }

            for (String textKey : getPossibleTextValueKeys()) {
                try {
                    perkStyle.getTextData().put(textKey, perkStyleJSON.getJSONObject(i).getString(textKey));
                } catch (Exception e) {
                    perkStyle.getTextData().put(textKey, null);
                }
            }

            var selectionsJSON = perkStyleJSON.getJSONObject(i).getJSONArray("selections");
            for (var j = 0; j < selectionsJSON.length(); j++) {
                PerkStyleSelection perkStyleSelection = new PerkStyleSelection();

                for (String longKey : getPossibleNumericValueKeys()) {
                    try {
                        perkStyleSelection.getLongValues().put(longKey, selectionsJSON.getJSONObject(j).getLong(longKey));
                    } catch (Exception e) {
                        perkStyleSelection.getLongValues().put(longKey, null);
                    }
                }

                perkStyle.getSelections().add(perkStyleSelection);
            }

            if (i == 0)
            {
                perks.setPrimaryStyle(perkStyle);
            }
            else if (i == 2)
            {
                perks.setSubStyle(perkStyle);
            }
        }

        return perks;
    }
}
