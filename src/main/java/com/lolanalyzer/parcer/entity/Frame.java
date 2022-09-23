package com.lolanalyzer.parcer.entity;


import com.lolanalyzer.parcer.entytiId.EventId;
import com.lolanalyzer.parcer.entytiId.FrameId;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
@Slf4j
@Entity
public class Frame {

    @EmbeddedId
    FrameId id;

    @OneToMany
    List<ParticipantFrame> participantFrames;

    @OneToMany
    List<EventId> events;


}
