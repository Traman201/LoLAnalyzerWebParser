package com.lolanalyzer.parcer.controller;

import com.lolanalyzer.parcer.entity.Game;
import com.lolanalyzer.parcer.repositiory.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@Slf4j
@RequestMapping("/game")
public class GameController {

    private GameRepository gameRepository;

    @Autowired
    public GameController(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }
    @GetMapping
    public String gameForm(Model model){
        ArrayList<Game> games = (ArrayList<Game>) gameRepository.findAll();
        model.addAttribute("games", games);

        return "game";
    }

    @PostMapping()
    public String addGame(@RequestParam String matchID, @RequestParam String apiKey) {

        Game game = new Game();

        gameRepository.save(game);


        return "redirect:/game";
    }


}
