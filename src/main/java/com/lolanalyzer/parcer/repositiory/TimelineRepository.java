package com.lolanalyzer.parcer.repositiory;

import com.lolanalyzer.parcer.entity.Timeline;
import com.lolanalyzer.parcer.entytiId.MatchId;
import org.springframework.data.repository.CrudRepository;

import java.sql.Time;
import java.util.Optional;

public interface TimelineRepository extends CrudRepository<Timeline, MatchId> {

    Timeline save(Timeline timeline);

    Optional<Timeline> findById(MatchId id);
}
