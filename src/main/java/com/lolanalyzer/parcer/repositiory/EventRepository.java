package com.lolanalyzer.parcer.repositiory;

import com.lolanalyzer.parcer.entity.events.Event;
import com.lolanalyzer.parcer.entytiId.EventId;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, EventId> {

    Event save(Event event);
}
