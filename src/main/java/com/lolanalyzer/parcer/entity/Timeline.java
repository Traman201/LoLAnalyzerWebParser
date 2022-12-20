package com.lolanalyzer.parcer.entity;


import com.lolanalyzer.parcer.entytiId.MatchId;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Slf4j
@Setter
@Getter
@Entity
public class Timeline {

    @EmbeddedId
    MatchId id;

    long frameInterval;

    @OneToMany
    List<Frame> frames;

}
