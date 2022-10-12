package com.lolanalyzer.parcer.repositiory;

import com.lolanalyzer.parcer.entytiId.MatchId;
import com.lolanalyzer.parcer.entity.Participant;
import com.lolanalyzer.parcer.entytiId.ParticipantId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ParticipantRepository extends CrudRepository<Participant, ParticipantId> {


    Participant save(Participant participant);

    Optional<Participant> findById(ParticipantId id);

    Iterable<Participant> findAllByIdMatchId(MatchId id);

}
