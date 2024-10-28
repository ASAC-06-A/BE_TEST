package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.roadmapDto.RoadmapResponseDto;
import com.asac.study_hub.domain.Roadmap;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.repository.RoadmapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoadmapService {

    RoadmapRepository roadmapRepository;

    public List<RoadmapResponseDto> findAll(User user) {
        List<Roadmap> roadmapList = roadmapRepository.findByUser(user);
        List<RoadmapResponseDto> roadmapResponseDtoList = new ArrayList<>();
        roadmapList.forEach(roadmap -> {
            RoadmapResponseDto responseDto = RoadmapResponseDto.to(roadmap);
            roadmapResponseDtoList.add(responseDto);
        });
        return roadmapResponseDtoList;
    }
}
