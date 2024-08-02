package pairmatching.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import pairmatching.course.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;

public class CheckInput {

    public static Input makePairInput(String inputStr) {

        String[] inputs = inputStr.split(",");

        if (inputs.length != 3) {
            throw new IllegalArgumentException("[ERROR] 올바른 값을 입력하세요.");
        }

        return getInput(inputs);
    }

    private static Input getInput(String[] inputs) {
        Course course = Arrays.stream(Course.values())
            .filter(c -> c.getName().equals(inputs[0].trim()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 값을 입력하세요."));
        Level level = Arrays.stream(Level.values())
            .filter(l -> l.getName().equals(inputs[1].trim()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 값을 입력하세요."));
        Mission mission = Arrays.stream(Mission.values())
            .filter(m -> m.getName().equals(inputs[2].trim()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 값을 입력하세요."));
        if (!level.getMissions().contains(mission)) {
            throw new IllegalArgumentException("[ERROR] 올바른 값을 입력하세요.");
        }

        return new Input(course, level, mission);
    }

    public static void checkMainInput(String input) {
        Set<String> checkStrings = new HashSet<>(Arrays.asList("1", "2", "3", "Q"));
        if (!checkStrings.contains(input)) {
            throw new IllegalArgumentException("[ERROR] 올바른 값을 입력하세요.");
        }
    }

}
