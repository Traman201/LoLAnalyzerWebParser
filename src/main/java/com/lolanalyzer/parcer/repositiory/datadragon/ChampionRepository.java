package com.lolanalyzer.parcer.repositiory.datadragon;

import com.lolanalyzer.parcer.entity.datadragon.ChampionStats;
import org.springframework.data.repository.CrudRepository;

public interface ChampionRepository extends CrudRepository<ChampionStats, String> {

}
