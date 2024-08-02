package pairmatching.course;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.util.MakeCrew;

public class Frontend implements Crew {

    private final List<String> initFrontCrews;
    private final Map<Mission, Set<String>> resultsByMission = new HashMap<>();
    private final Map<Level, Set<String>> resultsByLevel = new HashMap<>();

    protected Frontend(MakeCrew makeCrew) {
        this.initFrontCrews = makeCrew.makeCrewList("backend-crew.md");
        Arrays.stream(Mission.values())
            .forEach(mission -> resultsByMission.put(mission, new HashSet<>()));
        Arrays.stream(Level.values())
            .forEach(level -> resultsByLevel.put(level, new HashSet<>()));
    }

    @Override
    public Set<String> getResultsByMission(Mission mission) {
        return resultsByMission.get(mission);
    }

    @Override
    public Set<String> getResultsByLevel(Level level) {
        return resultsByLevel.get(level);
    }

    @Override
    public List<String> getShuffledCrews() {
        return Randoms.shuffle(initFrontCrews);
    }

}
