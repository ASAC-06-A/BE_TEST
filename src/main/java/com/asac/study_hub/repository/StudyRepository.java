package com.asac.study_hub.repository;

import com.asac.study_hub.domain.Category;
import com.asac.study_hub.domain.Study;
import com.asac.study_hub.domain.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Repository
public class StudyRepository implements StudyIRepository{
    /**
     * TODO: StudyIRepository 구현
     * findAll() 메서드 구현: studyList 해시앱에 있는 value 값 List 로 반환
     */

    Study study;
    static final HashMap<Integer, Study> studyList;

    static {
        studyList = new HashMap<>();
        User user = new User(1, "jiyeon", "jiyeon@gmail.com", "jiyeon7890");
        studyList.put(1, Study.builder()
                .id(1)
                .category(new Category("Backend"))
                .title("spring basic1")
                .studyLink("https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard")
                .description("인프런 스프링 기초 강의")
                .user(user)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .order(1)
                .build());
        studyList.put(2, Study.builder()
                .id(2)
                .category(new Category("Backend"))
                .title("spring basic2")
                .studyLink("https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard")
                .description("인프런 스프링 기초 강의")
                .user(user)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .order(2)
                .build());
        studyList.put(3, Study.builder()
                .id(1)
                .category(new Category("Backend"))
                .title("spring basic3")
                .studyLink("https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard")
                .description("인프런 스프링 기초 강의")
                .user(user)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .order(3)
                .build());

    }

    public List<Study> findAll() {
        return List.of();
    }

    public Optional<Study> findById(Integer studyId) {
        return studyList.values().stream().filter((study) -> studyId.equals(study.getId())).findFirst();
    }

    public Integer save(Study study) {
        studyList.put(getId()+1, study);
        return getId();
    }

    public Integer getId() {
        return studyList.size();
    }

    public List<Study> findByCategory(String category) {
        return studyList.values().stream()
                .filter((study) -> category.equals(study.getCategory().getCategory()))
                .toList();
    }

    public void update(Study study, Study newStudy) {
        study.update(newStudy);
    }

    public void deleteAll(List<Study> study) {
        studyList.values().removeAll(study);
        reassignId(); //idGenerator
    }

    private void reassignId() {
        //강의 삭제후 HashMap id 재정렬
        HashMap<Integer, Study> tempList = new HashMap<>();
        Integer id = 1;
        for (Study study : studyList.values()) {
            tempList.put(id++, study);
        }

        studyList.clear();
        for (Study study : tempList.values()) {
            studyList.put(id++, study);
        }
    }

    public Optional<Study> findByIdAndUser(Integer id, User user) {
        return studyList.values().stream()
                .filter((study) -> id.equals(study.getId()) && user.equals(study.getUser()))
                .findAny();
    }

}
