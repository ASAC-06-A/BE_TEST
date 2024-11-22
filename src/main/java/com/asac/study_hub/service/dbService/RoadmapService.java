package com.asac.study_hub.service.dbService;

import com.asac.study_hub.repository.dbRepository.RoadmapRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class RoadmapService {

    RoadmapRepository roadmapRepository;
}
