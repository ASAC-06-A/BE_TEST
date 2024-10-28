package com.asac.study_hub.repository;

import com.asac.study_hub.domain.Category;
import com.asac.study_hub.domain.Roadmap;
import com.asac.study_hub.domain.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RoadmapRepository {

    private static HashMap<Integer, Roadmap> roadmapList;

    public List<Roadmap> findByUser(User user) {
        List<Roadmap> roadmaps = roadmapList.values().stream()
                .filter((roadmap) -> user.equals(roadmap.getUser()))
                .toList();
        return roadmaps;
    }
}
