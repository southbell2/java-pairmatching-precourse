package pairmatching.feature.command;

import pairmatching.pairmatching.PairMatching;

public class ClearPairMatchingCommand implements Command {

    @Override
    public void execute() {
        PairMatching.clearAllMatching();
    }
}
