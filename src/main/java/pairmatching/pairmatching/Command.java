package pairmatching.pairmatching;

public interface Command {

    void execute();

    default void showPrompt() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
            .append("#############################################\n")
            .append("과정: 백엔드 | 프론트엔드\n")
            .append("미션:\n")
            .append("  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n")
            .append("  - 레벨2: 장바구니 | 결제 | 지하철노선도\n")
            .append("  - 레벨3:\n")
            .append("  - 레벨4: 성능개선 | 배포\n")
            .append("  - 레벨5:\n")
            .append("############################################\n")
            .append("과정, 레벨, 미션을 선택하세요.");
        System.out.println(stringBuilder);
    }

    class Input {

        protected final String course;
        protected final String level;
        protected final String mission;

        public Input(String course, String level, String mission) {
            this.course = course;
            this.level = level;
            this.mission = mission;
        }

    }
}
