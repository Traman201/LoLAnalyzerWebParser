package com.lolanalyzer.parcer.service;

import com.lolanalyzer.parcer.service.game.Champion;
import com.lolanalyzer.parcer.service.game.Team;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@Getter
public class TeamDiffCalculator {

    Team order, chaos;

    public void setTeams(Team order, Team chaos){
        this.order = order;
        this.chaos = chaos;
    }
    public Champion findChampion(String summonerName){
        for (Champion champion : order.getChampions()){
            if(champion.getSummonerName().equals(summonerName)){
                return champion;
            }
        }
        for(Champion champion : chaos.getChampions()){
            if(champion.getSummonerName().equals(summonerName)){
                return champion;
            }
        }
        log.error("Could not find a champion with name " + summonerName);
        return null;
    }

    public Map<String, Double> teamDiff(){
        Map<String, Double> diff = new HashMap<>();
        order.recalculateStats();
        chaos.recalculateStats();

        for(int i = 0; i < 5; i++){
            championDiff(chaos.getChampions().get(i), order.getChampions().get(i))
                    .forEach((k, v) -> diff.merge(k, v, Double::sum));
        }

        return diff;
    }


    public Map<String, Double> championDiff(Champion chaos, Champion order){

        Map<String, Double> championDiff = new HashMap<>();
        for(String key : chaos.getStats().keySet()){
            double statDiff = 0;
            statDiff += chaos.getStats().get(key);
            statDiff -= order.getStats().get(key);
            championDiff.put(key, statDiff);
        }
        for(String key : chaos.getScore().keySet()){
            double scoreDiff = 0;
            scoreDiff += chaos.getScore().get(key);
            scoreDiff -= order.getScore().get(key);
            championDiff.put(key, scoreDiff);
        }
        return championDiff;
    }


}
