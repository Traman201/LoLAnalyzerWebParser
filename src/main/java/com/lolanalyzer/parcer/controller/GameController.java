package com.lolanalyzer.parcer.controller;

import com.lolanalyzer.parcer.entity.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/game")
public class GameController {

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game){

        log.info(game.toString());
        return null;
    }

    @GetMapping
    public String gameForm(){
        return "game";
    }
}
