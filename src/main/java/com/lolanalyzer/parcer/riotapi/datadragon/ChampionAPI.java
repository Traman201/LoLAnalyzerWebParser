package com.lolanalyzer.parcer.riotapi.datadragon;

import com.lolanalyzer.parcer.entity.datadragon.ChampionStats;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChampionAPI {

    public static String[] getStatKeys(){
        return new String[] {
                "hp",
                "hpperlevel",
                "mp",
                "mpperlevel",
                "movespeed",
                "armor",
                "armorperlevel",
                "spellblock",
                "spellblockperlevel",
                "attackrange",
                "hpregen",
                "hpregenperlevel",
                "mpregen",
                "mpregenperlevel",
                "crit",
                "critperlevel",
                "attackdamage",
                "attackdamageperlevel",
                "attackspeedperlevel",
                "attackspeed"
        };
    }

    public static Map<String, String> champToParticipant(){
        Map<String, String> conversion = new HashMap<>();

        conversion.put("hp", "healthMax");
        conversion.put("mp", "powerMax");
        conversion.put("movespeed", "movementSpeed");
        conversion.put("armor", "armor");
        conversion.put("spellblock", "magicResist");
        conversion.put("hpregen", "healthRegen");
        conversion.put("mpregen", "powerRegen");
        conversion.put("attackdamage", "attackDamage");
        conversion.put("attackspeed", "attackSpeed");

        return conversion;
    }
    public static Map<String, String> statToStatPerLevel(){
        Map<String, String> conversion = new HashMap<>();

        conversion.put("hp", "hpperlevel");
        conversion.put("mp", "mpperlevel");
        conversion.put("armor", "armorperlevel");
        conversion.put("spellblock", "spellblockperlevel");
        conversion.put("hpregen", "hpregenperlevel");
        conversion.put("mpregen", "mpregenperlevel");
        conversion.put("attackdamage", "attackdamageperlevel");

        return conversion;
    }

    public static ChampionStats parseChampion(JSONObject champJSON){
        ChampionStats champion = new ChampionStats();

        champion.setName(champJSON.getString("name"));
        champion.setId(champJSON.getString("id"));

        JSONObject statsJSON = champJSON.getJSONObject("stats");
        for(String statKey : getStatKeys()){
            champion.getStats().put(statKey,
                    statsJSON.getDouble(statKey));
        }
        return champion;
    }
}
