package pairmatching.domain;

public enum Mission {

    CAR_RACE("자동차경주"),
    LOTTO("로또"),
    NUMBER_BASEBALL("숫자야구게임"),
    SHOPPING_CART("장바구니"),
    PAYMENT("결제"),
    SUBWAY_MAP("지하철노선도"),
    PERFORM_IMPROVEMENT("성능개선"),
    DEPLOY("배포");

    private final String name;

    Mission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
