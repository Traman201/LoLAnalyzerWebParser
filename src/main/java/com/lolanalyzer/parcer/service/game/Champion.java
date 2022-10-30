package com.lolanalyzer.parcer.service.game;

import com.lolanalyzer.parcer.entity.datadragon.ChampionStats;
import com.lolanalyzer.parcer.entity.datadragon.ItemStats;
import com.lolanalyzer.parcer.riotapi.ParticipantFramesAPI;
import com.lolanalyzer.parcer.riotapi.datadragon.ChampionAPI;
import com.lolanalyzer.parcer.riotapi.datadragon.ItemAPI;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Champion {
    String summonerName;
    String championName;
    List<ItemStats> items;

    int level;
    Map<String, Double> stats;
    Map<String, Double> score;

    ChampionStats championStats;

    public Champion(ChampionStats championStats, String summonerName, String championName){
        stats = new HashMap<>();
        score = new HashMap<>();
        items = new ArrayList<>();

        for(String key : ParticipantFramesAPI.getChampionStatsKeys()){
            stats.put(key, 0.0);
        }

        score.put("kills", 0.0);
        score.put("assists", 0.0);
        score.put("deaths", 0.0);
        score.put("creepScore", 0.0);

        level = 1;

        this.championStats = championStats;
        this.summonerName = summonerName;
        this.championName = championName;

    }
    public void recalculateStats(){
        resetStats();
        Map<String, String> conversion = ChampionAPI.champToParticipant();
        for(Map.Entry<String, String> conv : conversion.entrySet()){
            updateBaseStats(conv.getKey(), conv.getValue());
        }
        addItemStats();
    }
    private void resetStats(){
        for(String key : ParticipantFramesAPI.getChampionStatsKeys()){
            stats.put(key, 0.0);
        }
    }
    public void setItems(List<ItemStats> items){
        this.items = items;
    }
    private void addItemStats(){
        Map<String, String> itemConversion = ItemAPI.championToItemConversion();
        for(ItemStats item : items){
            for(String key : item.getStats().keySet()){
                if(stats.get(itemConversion.get(key)) == null){
                    continue;
                }
                double stat = stats.get(itemConversion.get(key));
                stat += item.getStats().get(key);
                stats.put(itemConversion.get(key), stat);
            }
        }
    }
    private void updateBaseStats(String key, String value){
        Map<String, String> statConversion = ChampionAPI.statToStatPerLevel();
        double stat = stats.get(value);
        stat += championStats.getStats().get(key);
        if(statConversion.containsKey(key)){
            stat += championStats.getStats().get(statConversion.get(key)) * (level - 1);
        }
        stats.put(value, stat);
    }

}
