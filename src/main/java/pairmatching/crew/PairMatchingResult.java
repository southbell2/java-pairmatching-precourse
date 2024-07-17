package pairmatching.crew;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PairMatchingResult {

    private static final Map<String, Map<String, Set<String>>> resultsByMission = new HashMap<>();
    private static final Map<String, Map<String, Set<String>>> resultsByLevel = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        for (Course course : Course.values()) {
            resultsByLevel.put(course.getName(), new HashMap<>());
            resultsByMission.put(course.getName(), new HashMap<>());
            for (Level level : Level.values()) {
                resultsByLevel.get(course.getName()).put(level.getName(), new HashSet<>());
                for (String mission : level.getMissions()) {
                    resultsByMission.get(course.getName()).put(mission, new HashSet<>());
                }
            }
        }
    }

    public static boolean isEmptyMatching(String course, String mission) {
        Set<String> result = resultsByMission.get(course).get(mission);
        return result.isEmpty();
    }

    public static boolean existMatching(String course, String level, String pair) {
        Set<String> result = resultsByLevel.get(course).get(level);
        return result.contains(pair);
    }

    public static void clearMatchingMission(String course, String level, String mission) {
        Set<String> missionResult = resultsByMission.get(course).get(mission);
        Set<String> levelResult = resultsByLevel.get(course).get(level);
        for (String pair : missionResult) {
            levelResult.remove(pair);
        }
        missionResult.clear();
    }

    public static void addMatchingMission(String course, String mission, String level, String pair) {
        resultsByMission.get(course).get(mission).add(pair);
        resultsByLevel.get(course).get(level).add(pair);
    }

    public static void printMatching(String course, String mission) {
        Set<String> result = resultsByMission.get(course).get(mission);
        System.out.println("페어 매칭 결과입니다.");
        for (String pair : result) {
            System.out.println(pair);
        }
    }

    public static void clearAllMatching() {
        resultsByMission.clear();
        resultsByLevel.clear();
        init();
    }
}
