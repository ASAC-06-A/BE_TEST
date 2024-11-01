package com.asac.study_hub.repository;

import com.asac.study_hub.domain.Roadmap;
import com.asac.study_hub.domain.RoadmapStudy;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;

@Repository
public class RoadmapStudyRepository {
    private static final HashMap<Integer, RoadmapStudy> roadmapStudyList = new HashMap<>();

    public Integer save(RoadmapStudy roadmapStudy) {
        roadmapStudyList.put(roadmapStudyList.size() + 1, roadmapStudy);
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
