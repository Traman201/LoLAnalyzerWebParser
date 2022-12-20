package com.lolanalyzer.parcer.repositiory;

import com.lolanalyzer.parcer.entity.ParticipantFrame;
import com.lolanalyzer.parcer.entytiId.ParticipantFrameId;
import org.springframework.data.repository.CrudRepository;

public interface ParticipantFrameRepository extends CrudRepository<ParticipantFrame, ParticipantFrameId> {

    ParticipantFrame save(ParticipantFrame frame);
}
