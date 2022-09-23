package com.lolanalyzer.parcer.controller;

import com.lolanalyzer.parcer.entity.Timeline;
import com.lolanalyzer.parcer.repositiory.TimelineRepository;
import com.lolanalyzer.parcer.riotapi.MatchAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Time;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/timeline")
public class TimelineController {
    TimelineRepository timelineRepository;

    @Autowired
    public TimelineController(TimelineRepository timelineRepository){
        this.timelineRepository = timelineRepository;

    }


    @GetMapping("/{matchId}")
    public String timelineForm(@PathVariable String matchId, Model model){

        Optional<Timeline> timelineEntity = timelineRepository.findById(MatchAPI.constructMatchId(matchId));

        Timeline timeline = timelineEntity.get();

        model.addAttribute("timeline", timeline);
        return "timeline";
    }
}
