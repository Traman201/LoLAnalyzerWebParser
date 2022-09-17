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
    public String addGame(@RequestBody Game game) {

        log.info("Found game: " + game.getMatchId());

        return "redirect:/game";
    }

    /*@PostMapping("/add")
    public String addGame(@RequestBody String string) {
        RestTemplate restTemplate = new RestTemplate();
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = parser.parseMap(string);

        Map<String, Object> info, metadata;
        info = parser.parseMap(map.get("info").toString());
        metadata = parser.parseMap(map.get("metadata").toString());

        int i = 0;
        String entryNames = "";
        for(Map.Entry<String, Object> entry : metadata.entrySet()){
            entryNames += entry.getKey() + "; ";
            i++;
        }
        log.info("Found entries: " + i + " " + entryNames);

        return "redirect:/game";
    }*/

}
