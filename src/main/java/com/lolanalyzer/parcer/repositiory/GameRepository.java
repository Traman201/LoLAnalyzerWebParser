package com.lolanalyzer.parcer.repositiory;

import com.lolanalyzer.parcer.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, String> {

    Game save(Game game);

    Iterable<Game> findAll();
}
