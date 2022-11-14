package com.lolanalyzer.parcer.controller;

import com.lolanalyzer.parcer.controller.helpers.response.LocalGameStatus;
import com.lolanalyzer.parcer.riotapi.datadragon.ItemAPI;
import com.lolanalyzer.parcer.service.TeamDiffCalculator;
import com.lolanalyzer.parcer.service.game.Champion;
import com.lolanalyzer.parcer.service.game.LocalRequester;
import com.lolanalyzer.parcer.service.game.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/local")
public class LocalClientController {

    @Autowired
    TeamDiffCalculator calculator;

    @Autowired
    LocalRequester requester;


    @GetMapping
    public String localForm(Model model){
        String [] champions = new String[10];
        for(int i = 0; i < 10; i++){
            champions[i] = "Champion" + (i +1);
        }
        String[] values = ItemAPI.championToItemConversion().values().toArray(new String[0]);
        model.addAttribute("static/images/champions", champions);
        model.addAttribute("values", values);
        return "local";
    }

    @GetMapping("/status")
    public @ResponseBody LocalGameStatus getStatus(@RequestParam int index){
        LocalGameStatus localGameStatus = new LocalGameStatus();
        Team team = null;

        localGameStatus.setWorking(requester.isGameStarted());
        if(!requester.isGameStarted()){
            return localGameStatus;
        }
        if(calculator.getChaos().getChampions().size() + calculator.getOrder().getChampions().size() != 10){
            return localGameStatus;
        }
        if(index < 5){
            team = calculator.getOrder();
        }
        else if(index == 10){
            localGameStatus.setSummonerName("difference");
            var diff = calculator.teamDiff();

            localGameStatus.setAbilityPower(diff.get("abilityPower"));
            localGameStatus.setArmor(diff.get("armor"));
            localGameStatus.setAttackDamage(diff.get("attackDamage"));
            localGameStatus.setAttackSpeed(diff.get("attackSpeed"));
            localGameStatus.setHealthMax(diff.get("healthMax"));
            localGameStatus.setLifesteal(diff.get("lifesteal"));
            localGameStatus.setMagicResist(diff.get("magicResist"));
            localGameStatus.setMovementSpeed(diff.get("movementSpeed"));
            localGameStatus.setPowerMax(diff.get("powerMax"));

        }
        else{
            index -= 5;
            team = calculator.getChaos();
        }

        if(team != null){
            Champion champion = team.getChampions().get(index);
            localGameStatus.setSummonerName("(" + champion.getSummonerName() + ") " + champion.getChampionName());
            localGameStatus.setAbilityPower(champion.getStats().get("abilityPower"));
            localGameStatus.setArmor(champion.getStats().get("armor"));
            localGameStatus.setAttackDamage(champion.getStats().get("attackDamage"));
            localGameStatus.setAttackSpeed(champion.getStats().get("attackSpeed"));
            localGameStatus.setHealthMax(champion.getStats().get("healthMax"));
            localGameStatus.setLifesteal(champion.getStats().get("lifesteal"));
            localGameStatus.setMagicResist(champion.getStats().get("magicResist"));
            localGameStatus.setMovementSpeed(champion.getStats().get("movementSpeed"));
            localGameStatus.setPowerMax(champion.getStats().get("powerMax"));

        }


        return localGameStatus;
    }
}
