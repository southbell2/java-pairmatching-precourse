package pairmatching.feature.command;

import static pairmatching.pairmatching.PairMatching.addPairMatching;
import static pairmatching.pairmatching.PairMatching.clearMatchingMission;
import static pairmatching.pairmatching.PairMatching.isEmptyMatching;
import static pairmatching.pairmatching.PairMatching.printMatching;
import static pairmatching.prompt.MatchingPrompt.showPrompt;
import static pairmatching.util.CheckInput.makePairInput;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.util.Input;

public class PairMatchingCommand implements Command {

    @Override
    public void execute() {
        String command = "init";
        while (!command.equals("quit")) {
            showPrompt();
            command = Console.readLine();
            Input input;
            try {
                input = makePairInput(command);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (!isEmptyMatching(input.course, input.mission)) {
                rematchingPrompt();
                command = Console.readLine();
                if (!command.equals("예")) {
                    continue;
                }
                clearMatchingMission(input);
            }

            if (!makePairMatching(input)) {
                System.out.println("[ERROR] 매칭을 할 수 없습니다.");
                continue;
            }

            command = "quit";
        }
    }

    private void rematchingPrompt() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
            .append("매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n")
            .append("예 | 아니오");
        System.out.println(stringBuilder);
    }

    private boolean makePairMatching(Input input) {
        int count = 0;
        while (count < 3) {
            if (addPairMatching(input)) {
                //매칭 성공
                printMatching(input.course, input.mission);
                return true;
            }
            count++;
        }
        return false;
    }

}
