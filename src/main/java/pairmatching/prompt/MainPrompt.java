package pairmatching.prompt;

import static pairmatching.util.CheckInput.checkMainInput;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.feature.Invoker;

public class MainPrompt {

    private final Invoker invoker = new Invoker();

    public void startPairMatching() {
        String input ="init";
        while (!input.equals("Q")) {
            showMainPrompt();
            input = Console.readLine();
            try {
                checkMainInput(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            invoker.setCommand(input);
            invoker.executeCommand();
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


}
