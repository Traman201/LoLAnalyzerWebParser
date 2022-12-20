package com.lolanalyzer.parcer.controller;

import com.lolanalyzer.parcer.controller.helpers.response.LocalGameStatus;
import com.lolanalyzer.parcer.controller.helpers.response.NeuralResponse;
import com.lolanalyzer.parcer.riotapi.datadragon.ItemAPI;
import com.lolanalyzer.parcer.service.TeamDiffCalculator;
import com.lolanalyzer.parcer.service.game.Champion;
import com.lolanalyzer.parcer.service.game.LocalRequester;
import com.lolanalyzer.parcer.service.game.Team;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.ServletContextResource;

import javax.print.attribute.standard.Media;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;

/**
 * Контроллер эмуляции локального клиента
 * */
@Controller
@Slf4j
@RequestMapping("/local")
public class LocalClientController {

    @Autowired
    ServletContext context;

    @Autowired
    TeamDiffCalculator calculator;

    @Autowired
    LocalRequester requester;


    /**
     * Вызов макета local.html
     * */
    @GetMapping
    public String localForm(Model model){
        String [] champions = new String[10];
        for(int i = 0; i < 10; i++){
            champions[i] = "Champion" + (i +1);
        }
        String[] values = ItemAPI.championToItemConversion().values().toArray(new String[0]);
        model.addAttribute("champions", champions);
        model.addAttribute("values", values);
        return "local";
    }

    /**
     * Вывод информации о текущей игре в формате JSON
     *
     * <p>
     *     Ожидает, что количество игроков в текущей игре <b>равно 10</b>. В противном случае возвращает пустой ответ.
     * </p>
     *
     * @param index Порядковый номер чемпиона
     * */
    @GetMapping("/status")
    public @ResponseBody LocalGameStatus getStatus(@RequestParam(required = false, defaultValue = "10") int index){
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
            localGameStatus.setKill(diff.get("kills"));
            localGameStatus.setWinChance(requester.getWinChance());


        }
        else{
            index -= 5;
            team = calculator.getChaos();
        }

        if(team != null){
            Champion champion = team.getChampions().get(index);
            localGameStatus.setSummonerName("(" + champion.getSummonerName() + ") " + champion.getChampionName());
            localGameStatus.setChampionName(champion.getChampionName());
            localGameStatus.setAbilityPower(champion.getStats().get("abilityPower"));
            localGameStatus.setArmor(champion.getStats().get("armor"));
            localGameStatus.setAttackDamage(champion.getStats().get("attackDamage"));
            localGameStatus.setAttackSpeed(champion.getStats().get("attackSpeed"));
            localGameStatus.setHealthMax(champion.getStats().get("healthMax"));
            localGameStatus.setLifesteal(champion.getStats().get("lifesteal"));
            localGameStatus.setMagicResist(champion.getStats().get("magicResist"));
            localGameStatus.setMovementSpeed(champion.getStats().get("movementSpeed"));
            localGameStatus.setPowerMax(champion.getStats().get("powerMax"));
            localGameStatus.setKill(champion.getScore().get("kills"));
            localGameStatus.setRawChampionName(champion.getRawChampionName());

        }


        return localGameStatus;
    }

    @PostMapping("/status")
    public @ResponseBody NeuralResponse postWinChance(@RequestParam(required = false, defaultValue = "0") String winChance){
        NeuralResponse n = new NeuralResponse();
        requester.setWinChance(winChance);
        n.setKeepWorking(requester.isGameStarted());
        return n;
    }

}
