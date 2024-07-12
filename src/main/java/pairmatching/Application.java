package pairmatching;

public class Application {

    public static void main(String[] args) {
        // 초기화
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
        System.out.println(bootstrap.getBackCrews());
        System.out.println(bootstrap.getFrontCrews());
    }

}
