package pairmatching.pairmatching;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import pairmatching.crew.Course;
import pairmatching.crew.Level;
import pairmatching.crew.PairMatchingResult;


public class ShowPairCommand implements Command{

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

            if (PairMatchingResult.isEmptyMatching(input.course, input.mission)) {
                System.out.println("[ERROR] 매칭 이력이 없습니다.");
                continue;
            }

            PairMatchingResult.printMatching(input.course, input.mission);
            str = "quit";
        }
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
}
