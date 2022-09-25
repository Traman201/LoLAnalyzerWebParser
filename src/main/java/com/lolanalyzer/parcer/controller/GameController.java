package com.lolanalyzer.parcer.controller;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.Match;
import com.lolanalyzer.parcer.entity.Participant;
import com.lolanalyzer.parcer.entity.ParticipantFrame;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.repositiory.*;
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
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/game")
public class GameController {

    private GameRepository gameRepository;
    private ParticipantRepository participantRepository;
    private TimelineRepository timelineRepository;

    private FrameRepository frameRepository;

    private ParticipantFrameRepository participantFrameRepository;

    private EventRepository eventRepository;

    @Autowired
    public GameController(GameRepository gameRepository,
                          ParticipantRepository participantRepository,
                          TimelineRepository timelineRepository,
                          ParticipantFrameRepository participantFrameRepository,
                          FrameRepository frameRepository,
                          EventRepository eventRepository){
        this.gameRepository = gameRepository;
        this.participantRepository = participantRepository;
        this.timelineRepository = timelineRepository;
        this.frameRepository = frameRepository;
        this.participantFrameRepository = participantFrameRepository;
        this.eventRepository = eventRepository;

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
            saveMatch(match);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        return "redirect:/game";
    }

    private void saveMatch(Match match){
        for(Participant participant : match.getParticipants()){
            participantRepository.save(participant);
        }
        for(Frame frame : match.getTimeline().getFrames()){
            for(ParticipantFrame pf : frame.getParticipantFrames()){
                participantFrameRepository.save(pf);
            }
            for(Event event : frame.getEvents()){
                eventRepository.save(event);
            }
            frameRepository.save(frame);
        }
        timelineRepository.save(match.getTimeline());
        gameRepository.save(match);
    }


}
