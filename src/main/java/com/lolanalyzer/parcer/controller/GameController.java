package com.lolanalyzer.parcer.controller;

import com.lolanalyzer.parcer.controller.helpers.GameControllerMessenger;
import com.lolanalyzer.parcer.controller.helpers.Message;
import com.lolanalyzer.parcer.entity.Match;
import com.lolanalyzer.parcer.entity.Participant;
import com.lolanalyzer.parcer.riotapi.MatchAPI;
import com.lolanalyzer.parcer.riotapi.RiotAPIConfiguration;
import com.lolanalyzer.parcer.service.DataDump;
import com.lolanalyzer.parcer.service.MatchRepositoryManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@Slf4j
@RequestMapping("/game")
public class GameController {

    @Autowired
    MatchRepositoryManager matchManager;

    @Autowired
    GameControllerMessenger messenger;

    @Autowired
    DataDump dataDump;


    @GetMapping
    public String gameForm(Model model){
        ArrayList<Match> games = (ArrayList<Match>) matchManager.getGameRepository().findAll();
        model.addAttribute("games", games);
        model.addAttribute("apikey", RiotAPIConfiguration.getInstance().getApiKey());
        model.addAttribute("availableParses", dataDump.getTypes());


        if(messenger.hasMessages()){
            model.addAttribute("messages", messenger.getMessages());
        }

        return "game";
    }
    @GetMapping("/{matchId}")
    public String matchInfoForm(Model model, @PathVariable String matchId){

        ArrayList<Participant> participants = new ArrayList<>();

        participants = (ArrayList<Participant>) matchManager.getParticipantRepository().findAllByIdMatchId(MatchAPI.constructMatchId(matchId));
        model.addAttribute("participants", participants);

        return "matchInfo";
    }

    @PostMapping()
    public String addGame(@RequestParam String matchID, @RequestParam String apiKey) {
        RiotAPIConfiguration.getInstance().setApiKey(apiKey);
        long startTime = System.currentTimeMillis();
        long parsedTime;
        long savedTime;
        try {
            Match match = MatchAPI.getMatch(matchID);
            parsedTime = System.currentTimeMillis();
            log.info("[" + System.currentTimeMillis() + "]" + " DB writing");
            if(!matchManager.saveMatch(match)){
                messenger.addMessage("Ошибка записи в базу данных", Message.MessageType.ERROR);
            }
            else{
                messenger.addMessage("Запись успешно добавлена", Message.MessageType.SUCCESS);
            }
            savedTime = System.currentTimeMillis();

            log.info("Parsed in " + (parsedTime - startTime) / 1000.0 + " Saved in " + (savedTime - parsedTime) / 1000.0);
        } catch (IOException e) {
            messenger.addMessage("Ошибка получения информации о матче", Message.MessageType.ERROR);
        }


        return "redirect:/game";
    }

    @GetMapping("/dump")
    public String dumpGame(@RequestParam String dumpType){
        if(dataDump.dump(dumpType)){
            messenger.addMessage("Запись успешно сохранена", Message.MessageType.SUCCESS);
        }
        else {
            messenger.addMessage("Ошибка записи датасета", Message.MessageType.ERROR);
        }

        return "redirect:/game";

    }



}
