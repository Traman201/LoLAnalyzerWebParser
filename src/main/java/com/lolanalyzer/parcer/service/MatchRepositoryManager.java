package com.lolanalyzer.parcer.service;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entity.Match;
import com.lolanalyzer.parcer.entity.Participant;
import com.lolanalyzer.parcer.entity.ParticipantFrame;
import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entytiId.MatchId;
import com.lolanalyzer.parcer.repositiory.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@Getter
public class MatchRepositoryManager {
    private GameRepository gameRepository;
    private ParticipantRepository participantRepository;
    private TimelineRepository timelineRepository;

    private FrameRepository frameRepository;

    private ParticipantFrameRepository participantFrameRepository;

    private EventRepository eventRepository;

    @Autowired
    public MatchRepositoryManager(GameRepository gameRepository,
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


    @Transactional
    public boolean saveMatch(Match match){
        if(gameRepository.findById(match.getId()).isPresent()){
            return false;
        }
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
        return true;
    }

    public boolean hasMatch(Match match){
        return gameRepository.findById(match.getId()).isPresent();
    }
}
