package pairmatching.pairmatching;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import pairmatching.course.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.util.Input;

public class PairMatching {

    public static boolean isEmptyMatching(Course course, Mission mission) {
        Set<String> result = course.getCrew().getResultsByMission(mission);
        return result.isEmpty();
    }

    public static boolean addPairMatching(Input input) {
        List<String> shuffledCrews = input.course.getCrew().getShuffledCrews();
        for (int i = 0; i < shuffledCrews.size(); i += 2) {
            if (i == shuffledCrews.size() - 1) {
                break;
            }
            List<String> matchingList = new ArrayList<>();
            String pair;
            if (i + 3 == shuffledCrews.size()) {
                matchingList.add(shuffledCrews.get(i));
                matchingList.add(shuffledCrews.get(i + 1));
                matchingList.add(shuffledCrews.get(i + 2));
                matchingList.sort(Comparator.naturalOrder());
                pair =
                    matchingList.get(0) + " : " + matchingList.get(1) + " : " + matchingList.get(2);
            } else {
                matchingList.add(shuffledCrews.get(i));
                matchingList.add(shuffledCrews.get(i + 1));
                matchingList.sort(Comparator.naturalOrder());
                pair = matchingList.get(0) + " : " + matchingList.get(1);
            }

            if (existMatching(input.course, input.level, pair)) {
                clearMatchingMission(input);
                return false;
            }

            addMatchingMission(input, pair);
        }

        return true;
    }

    public static void printMatching(Course course, Mission mission) {
        Set<String> results = course.getCrew().getResultsByMission(mission);
        System.out.println("페어 매칭 결과입니다.");
        for (String pair : results) {
            System.out.println(pair);
        }
    }

    public static void clearAllMatching() {
        for (Course course : Course.values()) {
            for (Level level : Level.values()) {
                course.getCrew().getResultsByLevel(level).clear();
            }
            for (Mission mission : Mission.values()) {
                course.getCrew().getResultsByMission(mission).clear();
            }
        }
    }

    public static void clearMatchingMission(Input input) {
        Set<String> missionResult = input.course.getCrew().getResultsByMission(input.mission);
        Set<String> levelResult = input.course.getCrew().getResultsByLevel(input.level);
        for (String pair : missionResult) {
            levelResult.remove(pair);
        }
        missionResult.clear();
    }

    private static void addMatchingMission(Input input, String pair) {
        input.course.getCrew().getResultsByMission(input.mission).add(pair);
        input.course.getCrew().getResultsByLevel(input.level).add(pair);
    }

    private static boolean existMatching(Course course, Level level, String pair) {
        Set<String> result = course.getCrew().getResultsByLevel(level);
        return result.contains(pair);
    }
}
