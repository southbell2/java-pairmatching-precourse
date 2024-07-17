package pairmatching.pairmatching;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainPrompt {

    private final Invoker invoker = new Invoker();
    private final Command pairMatchingCommand = new PairMatchingCommand();
    private final Command showPairCommand = new ShowPairCommand();
    private final Command clearCommand = new ClearPairMatchingCommand();

    public void startPairMatching() {
        String input ="init";
        while (!input.equals("Q")) {
            showMainPrompt();
            input = Console.readLine();
            try {
                checkInput(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (input.equals("1")) {
                invoker.setCommand(pairMatchingCommand);
                invoker.executeCommand();
                continue;
            }
            if (input.equals("2")) {
                invoker.setCommand(showPairCommand);
                invoker.executeCommand();
                continue;
            }
            if (input.equals("3")) {
                invoker.setCommand(clearCommand);
                invoker.executeCommand();
            }
        }
    }

    private void showMainPrompt() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
            .append("기능을 선택하세요.\n")
            .append("1. 페어 매칭\n")
            .append("2. 페어 조회\n")
            .append("3. 페어 초기화\n")
            .append("Q. 종료");
        System.out.println(stringBuilder);
    }

    private void checkInput(String input) {
        Set<String> checkStrings = new HashSet<>(Arrays.asList("1", "2", "3", "Q"));
        if (!checkStrings.contains(input)) {
            throw new IllegalArgumentException("[ERROR] 올바른 값을 입력하세요.");
        }
    }

}
