package com.lolanalyzer.parcer.repositiory;

import com.lolanalyzer.parcer.entity.Match;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Match, String> {

    Match save(Match game);

    Iterable<Match> findAll();
}
