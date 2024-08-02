package pairmatching.feature.command;

import static pairmatching.prompt.MatchingPrompt.showPrompt;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.pairmatching.PairMatching;
import pairmatching.util.CheckInput;
import pairmatching.util.Input;


public class ShowPairCommand implements Command {

    @Override
    public void execute() {
        String command = "init";
        while (!command.equals("quit")) {
            showPrompt();
            command = Console.readLine();
            Input input;
            try {
                input = CheckInput.makePairInput(command);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (PairMatching.isEmptyMatching(input.course, input.mission)) {
                System.out.println("[ERROR] 매칭 이력이 없습니다.");
                continue;
            }

            PairMatching.printMatching(input.course, input.mission);
            command = "quit";
        }
    }
}
