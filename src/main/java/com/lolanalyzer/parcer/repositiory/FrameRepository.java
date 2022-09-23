package com.lolanalyzer.parcer.repositiory;

import com.lolanalyzer.parcer.entity.Frame;
import com.lolanalyzer.parcer.entytiId.FrameId;
import org.springframework.data.repository.CrudRepository;

public interface FrameRepository extends CrudRepository<Frame, FrameId> {

    Frame save(Frame frame);
}
