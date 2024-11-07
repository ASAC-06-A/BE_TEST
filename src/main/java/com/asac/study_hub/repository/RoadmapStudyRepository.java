package com.asac.study_hub.repository;

import com.asac.study_hub.domain.Roadmap;
import com.asac.study_hub.domain.RoadmapStudy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;

@Repository
@Slf4j
public class RoadmapStudyRepository {
    private static final HashMap<Integer, RoadmapStudy> roadmapStudyList = new HashMap<>();
    private Integer roadmapStudyId;

    public Integer save(RoadmapStudy roadmapStudy) {
        roadmapStudy.setId(roadmapStudyId);
        roadmapStudyList.put(roadmapStudyId, roadmapStudy);
        log.info("roadmapStudyId: {}", roadmapStudyId);
        roadmapStudyId++;
        return roadmapStudy.getId();
    }

    public void delete(RoadmapStudy roadmapStudy) {
        roadmapStudyList.values().remove(roadmapStudy);
        reassignId();
    }

    public List<RoadmapStudy> findByRoadmap(Roadmap roadmap) {
        return roadmapStudyList.values().stream()
                .filter(each -> each.equals(roadmap)).toList();
    }

    public void deleteAll(List<RoadmapStudy> roadmapStudys) {
        roadmapStudyList.values().removeAll(roadmapStudys);
        reassignId();
    }
    private void reassignId() {
        HashMap<Integer, RoadmapStudy> tempList = new HashMap<>();
        Integer id = 1;
        for (RoadmapStudy roadmapStudy : roadmapStudyList.values()) {
            tempList.put(id++, roadmapStudy);
        }

        roadmapStudyList.clear();
        for (RoadmapStudy study : roadmapStudyList.values()) {
            roadmapStudyList.put(id++, study);
        }
    }
}
