package com.asac.study_hub.repository;

import com.asac.study_hub.domain.Category;
import com.asac.study_hub.domain.Roadmap;
import com.asac.study_hub.domain.RoadmapStudy;
import com.asac.study_hub.domain.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoadmapRepository {

    UserRepository userRepository;
    private static HashMap<Integer, Roadmap> roadmapList = new HashMap<>();


//    static {
//        roadmapList = new HashMap<>();
//        roadmapList.put(1, new Roadmap(1, "roadmapTitle", new Category("Backend"), LocalDateTime.now(), LocalDateTime.now()))
//    }

    public List<Roadmap> findByUser(User user) {
        List<Roadmap> roadmaps = roadmapList.values().stream()
                .filter((roadmap) -> user.equals(roadmap.getUser()))
                .toList();
        return roadmaps;
    }

    public Integer save(Roadmap roadmap) {
        roadmapList.put(roadmapList.size() + 1, roadmap);
        return roadmap.getId();
    }

    public Optional<Roadmap> find(Roadmap roadmap) {
        return roadmapList.values().stream().filter(each -> each.equals(roadmap)).findFirst();
    }

    public Optional<Roadmap> findById(Integer roadmapId) {
        return roadmapList.values().stream().filter(each -> each.getId().equals(roadmapId)).findFirst();
    }

    public void delete(Roadmap roadmap) {
        roadmapList.values().remove(roadmap);
        reassignId();
    }

    private void reassignId() {
        HashMap<Integer, Roadmap> tempList = new HashMap<>();
        Integer id = 1;
        for (Roadmap study : roadmapList.values()) {
            tempList.put(id++, study);
        }

        roadmapList.clear();
        for (Roadmap study : roadmapList.values()) {
            roadmapList.put(id++, study);
        }
    }
}
