package pairmatching.feature;

import pairmatching.feature.command.ClearPairMatchingCommand;
import pairmatching.feature.command.Command;
import pairmatching.feature.command.PairMatchingCommand;
import pairmatching.feature.command.ShowPairCommand;

public class Invoker {

    private Command command;

    private final Command pairMatchingCommand = new PairMatchingCommand();
    private final Command showPairCommand = new ShowPairCommand();
    private final Command clearCommand = new ClearPairMatchingCommand();

    public void setCommand(String number) {
        switch (number) {
            case "1":
                this.command = pairMatchingCommand;
                break;
            case "2":
                this.command = showPairCommand;
                break;
            case "3":
                this.command = clearCommand;
                break;
        }
    }

    public void executeCommand() {
        command.execute();
    }

}
