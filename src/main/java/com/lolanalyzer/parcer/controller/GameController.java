package com.lolanalyzer.parcer.controller;

import com.lolanalyzer.parcer.entity.Match;
import com.lolanalyzer.parcer.entity.Participant;
import com.lolanalyzer.parcer.repositiory.GameRepository;
import com.lolanalyzer.parcer.repositiory.ParticipantRepository;
import com.lolanalyzer.parcer.riotapi.MatchAPI;
import com.lolanalyzer.parcer.riotapi.RiotAPIConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/game")
public class GameController {

    private GameRepository gameRepository;
    private ParticipantRepository participantRepository;

    @Autowired
    public GameController(GameRepository gameRepository, ParticipantRepository participantRepository){
        this.gameRepository = gameRepository;
        this.participantRepository = participantRepository;
    }
    @GetMapping
    public String gameForm(Model model){
        ArrayList<Match> games = (ArrayList<Match>) gameRepository.findAll();
        model.addAttribute("games", games);

        return "game";
    }
    @GetMapping("/{matchId}")
    public String matchInfoForm(Model model, @PathVariable String matchId){

        ArrayList<Participant> participants = new ArrayList<>();

        participants = (ArrayList<Participant>) participantRepository.findAllByIdMatchId(MatchAPI.constructMatchId(matchId));
        model.addAttribute("participants", participants);

        return "matchInfo";
    }

    @PostMapping()
    public String addGame(@RequestParam String matchID, @RequestParam String apiKey) {
        RiotAPIConfiguration.getInstance().setApiKey(apiKey);

        try {
            Match match = MatchAPI.getMatch(matchID);
            for(Participant participant : match.getParticipants()){
                participantRepository.save(participant);
            }
            gameRepository.save(match);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        return "redirect:/game";
    }


}
