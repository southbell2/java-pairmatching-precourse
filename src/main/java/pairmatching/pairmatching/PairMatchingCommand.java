package pairmatching.pairmatching;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import pairmatching.Bootstrap;
import pairmatching.crew.Course;
import pairmatching.crew.Level;
import pairmatching.crew.PairMatchingResult;

public class PairMatchingCommand implements Command {

    @Override
    public void execute() {
        String str = "init";
        while (!str.equals("quit")) {
            showPrompt();
            str = Console.readLine();
            String[] inputs = str.split(",");
            Input input;
            try {
                input = checkInput(inputs);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (!PairMatchingResult.isEmptyMatching(input.course, input.mission)) {
                rematchingPrompt();
                str = Console.readLine();
                if (!str.equals("예")) {
                    continue;
                }
                PairMatchingResult.clearMatchingMission(input.course, input.level, input.mission);
            }

            if (!makePairMatching(input)) {
                System.out.println("[ERROR] 매칭을 할 수 없습니다.");
                continue;
            }

            str = "quit";
        }
    }

    private void rematchingPrompt() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
            .append("매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n")
            .append("예 | 아니오");
        System.out.println(stringBuilder);
    }

    private Input checkInput(String[] inputs) {
        if (inputs.length != 3) {
            throw new IllegalArgumentException("[ERROR] 올바른 값을 입력하세요.");
        }

        Input input = new Input(inputs[0].trim(), inputs[1].trim(), inputs[2].trim());

        if (Arrays.stream(Course.values()).noneMatch(c -> c.getName().equals(input.course))) {
            throw new IllegalArgumentException("[ERROR] 올바른 값을 입력하세요.");
        }

        if (Arrays.stream(Level.values()).noneMatch(l -> l.getName().equals(input.level))) {
            throw new IllegalArgumentException("[ERROR] 올바른 값을 입력하세요.");
        }

        Level inputLevel = Level.valueOf("LEVEL" + input.level.charAt(2));
        if (!inputLevel.getMissions().contains(input.mission)) {
            throw new IllegalArgumentException("[ERROR] 올바른 값을 입력하세요.");
        }

        return input;
    }

    private boolean makePairMatching(Input input) {
        int count = 0;
        while (count < 3) {
            List<String> shuffledCrew;
            if (input.course.equals("백엔드")) {
                shuffledCrew = Randoms.shuffle(Bootstrap.backCrews);
            } else {
                shuffledCrew = Randoms.shuffle(Bootstrap.frontCrews);
            }

            if (!addPairMatching(shuffledCrew, input)) {
                //매칭 성공
                PairMatchingResult.printMatching(input.course, input.mission);
                return true;
            }
            count++;
        }
        return false;
    }

    private boolean addPairMatching(List<String> shuffledCrew, Input input) {
        for (int i = 0; i < shuffledCrew.size(); i += 2) {
            if(i == shuffledCrew.size()-1) break;
            List<String> matchingList = new ArrayList<>();
            String pair;
            if (i + 3 == shuffledCrew.size()) {
                matchingList.add(shuffledCrew.get(i));
                matchingList.add(shuffledCrew.get(i + 1));
                matchingList.add(shuffledCrew.get(i + 2));
                matchingList.sort(Comparator.naturalOrder());
                pair = matchingList.get(0) + " : " + matchingList.get(1) + " : " + matchingList.get(2);
            } else {
                matchingList.add(shuffledCrew.get(i));
                matchingList.add(shuffledCrew.get(i + 1));
                matchingList.sort(Comparator.naturalOrder());
                pair = matchingList.get(0) + " : " + matchingList.get(1);
            }

            if (PairMatchingResult.existMatching(input.course, input.level, pair)) {
                PairMatchingResult.clearMatchingMission(input.course, input.level, input.mission);
                return true;
            }

            PairMatchingResult.addMatchingMission(input.course, input.mission, input.level, pair);
        }

        return false;
    }

}
