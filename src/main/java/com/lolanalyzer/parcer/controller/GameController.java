package com.lolanalyzer.parcer.controller;

import com.lolanalyzer.parcer.entity.Game;
import com.lolanalyzer.parcer.repositiory.GameRepository;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.json.JSONObject;
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
    public String addGame(@RequestBody String gameInfo) {

        JSONObject root = new JSONObject(gameInfo);
        JSONObject metadata = root.getJSONObject("metadata");

        JSONObject info = root.getJSONObject("info");

        Game game = new Game();

        game.setGameCreation(info.getLong("gameCreation"));
        game.setGameDuration(info.getLong("gameDuration"));
        game.setGameMode(info.getString("gameMode"));
        game.setGameName(info.getString("gameName"));
        game.setGameType(info.getString("gameType"));
        game.setGameVersion(info.getString("gameVersion"));
        game.setGameEndTimestamp(info.getLong("gameEndTimeStamp"));
        game.setGameStartTimestamp(info.getLong("gameStartTimeStamp"));
        game.setMatchId(metadata.getString("matchId"));

        gameRepository.save(game);

        log.info(metadata.getString("matchId"));

        return "redirect:/game";
    }


}
