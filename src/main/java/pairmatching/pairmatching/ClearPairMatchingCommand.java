package pairmatching.pairmatching;

import pairmatching.crew.PairMatchingResult;

public class ClearPairMatchingCommand implements Command{

    @Override
    public void execute() {
        PairMatchingResult.clearAllMatching();
    }
}
