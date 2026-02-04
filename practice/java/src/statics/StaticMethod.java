package statics;

public class StaticMethod {
    private int instanceVal;
    private static int staticVal;

    public static void staticCall() {
        //instanceVal++;   // 정적메서드에서는 인스턴스 변수, 메서드 호출 불가
        //instanceMethod();

        staticVal++;
        staticMethod();
    }

    public void instanceCall() {
        instanceVal++;
        instanceMethod();

        staticVal++;
        staticCall();
    }

    private void instanceMethod() {
        System.out.println("instanceVal=" + instanceVal);
    }

    private static void staticMethod() {
        System.out.println("staticVal=" + staticVal);
    }
}
