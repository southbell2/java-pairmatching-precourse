package pairmatching.domain;

import static pairmatching.domain.Mission.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Level {
    LEVEL1("레벨1", Collections.unmodifiableList(Arrays.asList(CAR_RACE, LOTTO, NUMBER_BASEBALL))),
    LEVEL2("레벨2", Collections.unmodifiableList(Arrays.asList(SHOPPING_CART, PAYMENT, SUBWAY_MAP))),
    LEVEL3("레벨3", Collections.emptyList()),
    LEVEL4("레벨4", Collections.unmodifiableList(Arrays.asList(PERFORM_IMPROVEMENT, DEPLOY))),
    LEVEL5("레벨5", Collections.emptyList());

    private final String name;
    private final List<Mission> missions;

    Level(String name, List<Mission> missions) {
        this.name = name;
        this.missions = missions;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public String getName() {
        return name;
    }
}
