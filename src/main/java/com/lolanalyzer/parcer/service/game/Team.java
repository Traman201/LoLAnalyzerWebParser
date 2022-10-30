package com.lolanalyzer.parcer.service.game;

import com.lolanalyzer.parcer.riotapi.ChallengesAPI;
import com.lolanalyzer.parcer.riotapi.ParticipantFramesAPI;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Team {

    List<Champion> champions;

    Map<String, Long> objectives;

    public Team(ArrayList<Champion> champions){
        objectives = new HashMap<>();
        this.champions = champions;
    }
    public void recalculateStats(){
        for(Champion c : champions){
            c.recalculateStats();
        }
    }
}
