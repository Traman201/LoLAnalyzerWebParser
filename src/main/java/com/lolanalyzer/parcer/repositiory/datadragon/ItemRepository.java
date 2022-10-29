package com.lolanalyzer.parcer.repositiory.datadragon;

import com.lolanalyzer.parcer.entity.datadragon.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
