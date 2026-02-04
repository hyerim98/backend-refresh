package nested.inner;

public class Car {
    private static int outClassVal = 3;
    private int outInstanceVal = 2;
    private String model;
    private int chargeLevel;
    private Engine engine;

    public Car(String model, int chargeLevel) {
        this.model = model;
        this.chargeLevel = chargeLevel;
        engine = new Engine();
    }

    public void start() {
        engine.start();
        System.out.println(model + " 시작 완료");
    }
    private class Engine {
        public void start() {
            System.out.println("충전 레벨 확인: " + chargeLevel);
            System.out.println(model + "의 엔진을 구동합니다");

            System.out.println(outClassVal); // 외부 클래스의 클래스 멤버에 접근 가능
            System.out.println(outInstanceVal); // 외부 클래스의 인스턴스 멤버에 접근 가능
        }
    }
}
