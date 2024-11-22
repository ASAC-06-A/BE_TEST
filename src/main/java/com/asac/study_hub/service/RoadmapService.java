package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.ListResponseDto;
import com.asac.study_hub.controller.dto.ResponseIdDto;
import com.asac.study_hub.controller.dto.roadmapDto.RoadmapRequestDto;
import com.asac.study_hub.controller.dto.roadmapDto.RoadmapResponseDto;
import com.asac.study_hub.controller.dto.roadmapDto.UpdateRoadmapRequestDto;
import com.asac.study_hub.controller.dto.studyDto.StudyIdRequestDto;
import com.asac.study_hub.controller.dto.studyDto.StudyResponseDto;
import com.asac.study_hub.domain.*;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import com.asac.study_hub.repository.RoadmapRepository;
import com.asac.study_hub.repository.RoadmapStudyRepository;
import com.asac.study_hub.repository.StudyRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class RoadmapService {

    RoadmapRepository roadmapRepository;
    RoadmapStudyRepository roadmapStudyRepository; //roadmapStudyRepository, service 둘중 어느걸 roadmapService에 주입해야할지 고민
    RoadmapStudyService roadmapStudyService;
    StudyRepository studyRepository;

    public ListResponseDto<RoadmapResponseDto> findAll(User user) {
        List<Roadmap> roadmapList = roadmapRepository.findByUser(user);
        List<RoadmapResponseDto> roadmapResponseDtoList = roadmapList.stream()
                .map(RoadmapResponseDto::to)
                .toList();
        return new ListResponseDto<>(roadmapResponseDtoList.size(), roadmapResponseDtoList);
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
        ListResponseDto<RoadmapResponseDto> responseDto = findAll(user);
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

    public RoadmapResponseDto findById(User user, Integer roadmapId) {
        if (!isActiveUser(user)) {
            throw new CustomException(ExceptionType.INVALID_AUTHORIZATION);
        }
        //RoadmapStudy 에서 Roadmap 관련 칼럼 삭제
        Roadmap roadmap = roadmapRepository.findById(roadmapId)
                .orElseThrow(() -> new CustomException(ExceptionType.NOT_FOUND_ROADMAP_BY_ID, roadmapId));
        ListResponseDto<StudyResponseDto> studyResponseDto = roadmapStudyService.findStudyByRoadmap(roadmap);
        RoadmapResponseDto responseDto = RoadmapResponseDto.to(roadmap);
        responseDto.setStudy(studyResponseDto);

        return responseDto;

    }

    public void saveAllStudy(User user, Integer roadmapId, StudyIdRequestDto studyIdList) {
        if (!isActiveUser(user)) {
            throw new CustomException(ExceptionType.INVALID_AUTHORIZATION);
        }
        List<Study> studyList = new ArrayList<>();
        studyIdList.getStudyId().forEach(studyId -> {
            Study study = studyRepository.findById(studyId).orElseThrow(() -> new CustomException(ExceptionType.NOT_FOUND_STUDY_BY_ID, studyId));
            studyList.add(study);
        });
        Roadmap roadmap = roadmapRepository.findById(roadmapId)
                .orElseThrow(() -> new CustomException(ExceptionType.NOT_FOUND_ROADMAP_BY_ID, roadmapId));

        roadmapStudyRepository.findByRoadmap(roadmap).forEach(roadmapStudy -> {
            if (studyIdList.getStudyId().contains(roadmapStudy.getStudy().getStudyId())) {
                throw new CustomException(ExceptionType.ALREADY_EXIST, roadmapStudy.getStudy().getStudyId());
            }
        });

        studyList.forEach(study -> roadmapStudyRepository.save(new RoadmapStudy(roadmap, study)));

    }

    public void deleteAllStudy(User user, Integer roadmapId, StudyIdRequestDto studyIdList) {
        if (!isActiveUser(user)) {
            throw new CustomException(ExceptionType.INVALID_AUTHORIZATION);
        }

        //삭제할 study가 없다면
        Roadmap roadmap = roadmapRepository.findById(roadmapId).orElseThrow(() -> new CustomException(ExceptionType.NOT_FOUND_ROADMAP_BY_ID, roadmapId));
        List<RoadmapStudy> byRoadmap = roadmapStudyRepository.findByRoadmap(roadmap);
//        byRoadmap.forEach(roadmapStudy -> {
//            if (!studyIdList.getStudyId().contains(Integer.parseInt(String.valueOf(roadmapStudy.getStudy().getId())))) {
//                throw new CustomException(ExceptionType.NOT_EXIST_STUDY, roadmapStudy.getStudy().getId());
//            }
//        });
        //삭제할 raodmapstudy
        List<RoadmapStudy> findList = new ArrayList<>();
        studyIdList.getStudyId().forEach(studyId -> {
            Study findStudy = studyRepository.findById(studyId).orElseThrow(() -> new CustomException(ExceptionType.NOT_FOUND_STUDY_BY_ID, studyId));
            byRoadmap.forEach(each -> {
                if(each.getStudy().equals(findStudy)) findList.add(each);
            });
        });
        log.info("request studyId: {}", studyIdList.getStudyId().get(0));
        findList.forEach(each -> log.info("delete list: {}", each.toString()));

        roadmapStudyRepository.deleteAll(findList);

    }
    private boolean isActiveUser(User user) { //-> user가 write 작업할 때 사용해야할 메서드
        //로드맵 생성시 사용자 검사하는 이유: 유저가 탈퇴했는데 유저 토큰 혹은 세션이 탈취되서 탈퇴한 후에도 요청을 보내서 로드맵을 생성할 수 있는 가능성이 있음
        return user.getUserStatus().equals(UserStatus.ACTIVE);
    }

    public void update(User user, Integer roadmapId, UpdateRoadmapRequestDto updateRoadmapRequestDto) {
        if (!isActiveUser(user)) {
            throw new CustomException(ExceptionType.INVALID_AUTHORIZATION);
        }
        Roadmap findRoadmap = roadmapRepository.findById(roadmapId).orElseThrow(() -> new CustomException(ExceptionType.NOT_FOUND_ROADMAP_BY_ID, roadmapId));

        if (!checkAuthorization(user, findRoadmap)) {
            throw new CustomException(ExceptionType.INVALID_AUTHORIZATION);
        }

        findRoadmap.update(updateRoadmapRequestDto.to());
    }

    private boolean checkAuthorization(User user, Roadmap roadmap) {
        //수정할 권한이 있는지 확인
        return roadmap.getUser().equals(user);
    }

}
