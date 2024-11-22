package com.asac.study_hub.repository.memoryRepository;

import com.asac.study_hub.domain.Roadmap;
import com.asac.study_hub.domain.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

//@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoadmapRepository {

    final UserRepository userRepository;
    private static final HashMap<Integer, Roadmap> roadmapList = new HashMap<>();
    private Integer roadmapId = 0;


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
        idGenerator();
//        roadmap.setId(roadmapId);
        roadmapList.put(roadmapList.size() + 1, roadmap);

        return roadmap.getRoadmapId();
    }

    public Optional<Roadmap> find(Roadmap roadmap) {
        return roadmapList.values().stream().filter(each -> each.equals(roadmap)).findFirst();
    }

    public Optional<Roadmap> findById(Integer roadmapId) {
        return roadmapList.values().stream().filter(each -> each.getRoadmapId().equals(roadmapId)).findFirst();
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
        for (Roadmap study : tempList.values()) {
            roadmapList.put(id++, study);
        }
    }

    private Integer idGenerator() {
        roadmapId++;
        return roadmapId;
    }
}
