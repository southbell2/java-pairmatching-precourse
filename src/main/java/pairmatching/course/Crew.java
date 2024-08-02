package pairmatching.course;

import java.util.List;
import java.util.Set;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;

public interface Crew {

    Set<String> getResultsByMission(Mission mission);

    Set<String> getResultsByLevel(Level level);

    List<String> getShuffledCrews();

}
