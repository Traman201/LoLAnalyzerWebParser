package com.lolanalyzer.parcer.repositiory.datadragon;

import com.lolanalyzer.parcer.entity.datadragon.Champion;
import org.springframework.data.repository.CrudRepository;

public interface ChampionRepository extends CrudRepository<Champion, String> {

}
