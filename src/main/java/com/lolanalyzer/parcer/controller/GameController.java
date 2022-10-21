package com.lolanalyzer.parcer.controller;

import com.lolanalyzer.parcer.entity.Match;
import com.lolanalyzer.parcer.entity.Participant;
import com.lolanalyzer.parcer.riotapi.MatchAPI;
import com.lolanalyzer.parcer.riotapi.RiotAPIConfiguration;
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


    @GetMapping
    public String gameForm(Model model){
        ArrayList<Match> games = (ArrayList<Match>) matchManager.getGameRepository().findAll();
        model.addAttribute("games", games);

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
            matchManager.saveMatch(match);
            savedTime = System.currentTimeMillis();

            log.info("Parsed in " + (parsedTime - startTime) / 1000.0 + " Saved in " + (savedTime - parsedTime) / 1000.0);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        return "redirect:/game";
    }



}
