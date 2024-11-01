package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.roadmapDto.RoadmapResponseDto;
import com.asac.study_hub.domain.Roadmap;
import com.asac.study_hub.domain.RoadmapStudy;
import com.asac.study_hub.domain.Status;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import com.asac.study_hub.repository.RoadmapRepository;
import com.asac.study_hub.repository.RoadmapStudyRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoadmapService {

    RoadmapRepository roadmapRepository;
    RoadmapStudyRepository roadmapStudyRepository; //roadmapStudyRepository, service 둘중 어느걸 roadmapService에 주입해야할지 고민

    public List<RoadmapResponseDto> findAll(User user) {
        List<Roadmap> roadmapList = roadmapRepository.findByUser(user);
        List<RoadmapResponseDto> roadmapResponseDtoList = new ArrayList<>();
        roadmapList.forEach(roadmap -> {
            RoadmapResponseDto responseDto = RoadmapResponseDto.to(roadmap);
            roadmapResponseDtoList.add(responseDto);
        });
        return roadmapResponseDtoList;
    }

    public void delete(User user, Integer roadmapId) {

        Roadmap roadmap = findById(roadmapId);
        //해당 로드맵을 삭제할 수 있는 유저인가
        if (!validUser(user, roadmap)) {
            throw new CustomException(ExceptionType.INVALID_AUTHORIZATION);
        }
        //로드맵만 만들고 해당 로드맵에 스터디를 추가하지 않으면  roadmapStudy 에 데이터가 들어가지않음 -> null일수도 있다는말, null이라고해서 예외 던지면안됨
        List<RoadmapStudy> roadmapStudyList = roadmapStudyRepository.findByRoadmap(roadmap);
        if (!roadmapStudyList.isEmpty()) {
            roadmapStudyRepository.deleteAll(roadmapStudyList); //로드맵 fk가 roadmapStudy 테이블에 있으므로 fk먼저 제거 후 로드맵 삭제가능
        }
        roadmapRepository.delete(roadmap);
    }

    private boolean validUser(User user, Roadmap roadmap) {
        /*if (!roadmap.getUser().equals(user)) {
            throw new CustomException(ExceptionType.INVALID_AUTHORIZATION);
        }*/ //예외 처리를 이 메서드에서 할지 메서드를 사용하는 곳에서 처리할지 고민
        return roadmap.getUser().equals(user) && user.getStatus().equals(Status.ACTIVE);
    }

    public Roadmap findById(Integer roadmapId) {
        //RoadmapStudy 에서 Roadmap 관련 칼럼 삭제
        Roadmap roadmap = roadmapRepository.findById(roadmapId)
                .orElseThrow(() -> new CustomException(ExceptionType.NOT_FOUND_ROADMAP_BY_ID, roadmapId));
        return roadmap;

    }
}
