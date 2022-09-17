package com.lolanalyzer.parcer.controller;

import com.lolanalyzer.parcer.entity.Game;
import com.lolanalyzer.parcer.repositiory.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

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

    @PostMapping("/add")
    public String addGame(@RequestBody String string) {
        RestTemplate restTemplate = new RestTemplate();
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = parser.parseMap(string);

        String mapArray[] = new String[map.size()];

        for(Map.Entry<String, Object> entry : map.entrySet()){
            log.info(entry.getKey() + " = " + entry.getValue());
        }

        return "redirect:/game";
    }

}
