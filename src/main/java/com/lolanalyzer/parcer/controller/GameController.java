package com.lolanalyzer.parcer.controller;

import com.lolanalyzer.parcer.entity.Game;
import com.lolanalyzer.parcer.repositiory.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/game/add")
    public String addGame(@RequestBody String string) {
        return string;
    }


    /*@PostMapping
    public String addGame(){

    }*/
}
