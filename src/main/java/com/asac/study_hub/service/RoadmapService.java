package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.ResponseIdDto;
import com.asac.study_hub.controller.dto.roadmapDto.RoadmapRequestDto;
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

        Roadmap roadmap = roadmapRepository.findById(roadmapId)
                .orElseThrow(() -> new CustomException(ExceptionType.NOT_FOUND_ROADMAP_BY_ID, roadmapId));
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

    public ResponseIdDto save(User user, RoadmapRequestDto roadmapRequestDto) {
        if (!isActiveUser(user)) {
            throw new CustomException(ExceptionType.INVALID_AUTHORIZATION);
        }
        roadmapRequestDto.setId(findAll(user).size() + 1);
        roadmapRequestDto.setUser(user);
       Integer roadmapId = roadmapRepository.save(roadmapRequestDto.to());
        return new ResponseIdDto(roadmapId);
    }

    private boolean validUser(User user, Roadmap roadmap) {
        /*if (!roadmap.getUser().equals(user)) {
            throw new CustomException(ExceptionType.INVALID_AUTHORIZATION);
        }*/ //예외 처리를 이 메서드에서 할지 메서드를 사용하는 곳에서 처리할지 고민
        return roadmap.getUser().equals(user) && isActiveUser(user);
    }

    public Roadmap findById(User user, Integer roadmapId) {
        if (!isActiveUser(user)) {
            throw new CustomException(ExceptionType.INVALID_AUTHORIZATION);
        }
        //RoadmapStudy 에서 Roadmap 관련 칼럼 삭제
        Roadmap roadmap = roadmapRepository.findById(roadmapId)
                .orElseThrow(() -> new CustomException(ExceptionType.NOT_FOUND_ROADMAP_BY_ID, roadmapId));

        return roadmap;

    }

    private boolean isActiveUser(User user) { //-> user가 write 작업할 때 사용해야할 메서드
        //로드맵 생성시 사용자 검사하는 이유: 유저가 탈퇴했는데 유저 토큰 혹은 세션이 탈취되서 탈퇴한 후에도 요청을 보내서 로드맵을 생성할 수 있는 가능성이 있음
        return user.getStatus().equals(Status.ACTIVE);
    }
}
