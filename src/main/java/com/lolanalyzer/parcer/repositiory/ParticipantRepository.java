package com.lolanalyzer.parcer.repositiory;

import com.lolanalyzer.parcer.entity.MatchId;
import com.lolanalyzer.parcer.entity.Participant;
import com.lolanalyzer.parcer.entity.ParticipantId;
import org.springframework.data.repository.CrudRepository;

import javax.servlet.http.Part;
import java.util.Optional;

public interface ParticipantRepository extends CrudRepository<Participant, ParticipantId> {


    Participant save(Participant participant);

    Optional<Participant> findById(ParticipantId id);

    Iterable<Participant> findAllByIdMatchId(MatchId id);

}
