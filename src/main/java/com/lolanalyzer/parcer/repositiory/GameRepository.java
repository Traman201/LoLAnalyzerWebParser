package com.lolanalyzer.parcer.repositiory;

import com.lolanalyzer.parcer.entity.Match;
import com.lolanalyzer.parcer.entity.MatchId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameRepository extends CrudRepository<Match, MatchId> {

    Match save(Match game);

    Iterable<Match> findAll();

    Optional<Match> findById(MatchId id);
}
