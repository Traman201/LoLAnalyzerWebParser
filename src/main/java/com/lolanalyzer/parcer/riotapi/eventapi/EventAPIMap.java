package com.lolanalyzer.parcer.riotapi.eventapi;

import com.lolanalyzer.parcer.riotapi.EventAPI;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class EventAPIMap {

    private static EventAPIMap instance = null;

    private static final String[] eventTypes = new String[]{
            "PAUSE_END", // 0
            "SKILL_LEVEL_UP", //1
            "ITEM_PURCHASED", // 2
            "WARD_PLACED",//3
            "ITEM_DESTROYED", // 4
            "CHAMPION_KILL", //5
            "LEVEL_UP", // 6
            "WARD_KILL", // 7
            "TURRET_PLATE_DESTROYED", // 8
            "ITEM_UNDO", // 9
            "ITEM_SOLD", // 10
            "CHAMPION_SPECIAL_KILL", //11
            "ELITE_MONSTER_KILL", // 12
            "BUILDING_KILL", //13
            "OBJECTIVE_BOUNTY_PRESTART", //14
            "GAME_END" //15
    };

    Map<String, AbstractEventAPI> eventAPIMap;

    private EventAPIMap(){
        eventAPIMap = new HashMap<>();
        eventAPIMap.put(eventTypes[0], new PauseEndAPI());
        eventAPIMap.put(eventTypes[1], new SkillLevelUpAPI());
        eventAPIMap.put(eventTypes[2], new ItemPurchasedAPI());
        eventAPIMap.put(eventTypes[3], new WardPlacedAPI());
        eventAPIMap.put(eventTypes[4], new ItemDestroyedAPI());
        eventAPIMap.put(eventTypes[5], new ChampionKillAPI());
        eventAPIMap.put(eventTypes[6], new LevelUpAPI());
        eventAPIMap.put(eventTypes[7], new WardKillAPI());
        eventAPIMap.put(eventTypes[8], new TurretPlateDestroyedAPI());
        eventAPIMap.put(eventTypes[9], new ItemUndoAPI());
        eventAPIMap.put(eventTypes[10], new ItemSoldAPI());
        eventAPIMap.put(eventTypes[11], new ChampionSpecialKillAPI());
        eventAPIMap.put(eventTypes[12], new EliteMonsterKillAPI());
        eventAPIMap.put(eventTypes[13], new BuildingKillAPI());
        eventAPIMap.put(eventTypes[14], new ObjectiveBountyPrestartAPI());
        eventAPIMap.put(eventTypes[15], new GameEndAPI());
    }

    public static EventAPIMap getInstance(){
        if(instance == null){
            instance = new EventAPIMap();
        }
        return instance;
    }
}
