package com.lolanalyzer.parcer.riotapi.datadragon;

import com.lolanalyzer.parcer.entity.datadragon.Champion;
import org.json.JSONObject;

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

    public static Champion parseChampion(JSONObject champJSON){
        Champion champion = new Champion();

        champion.setName(champJSON.getString("name"));
        champion.setId(champJSON.getString("id"));

        JSONObject statsJSON = champJSON.getJSONObject("stats");
        for(String statKey : getStatKeys()){
            champion.getStats().put(statKey,
                    statsJSON.getLong(statKey));
        }
        return champion;
    }
}
