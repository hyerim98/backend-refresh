package statics;

public class StaticMain {
    public static void main(String[] args) {
        StaticVar data1 = new StaticVar("A");
        System.out.println("data1: " + data1.count); // Data.count로 호출해도 같음

        StaticVar data2 = new StaticVar("B");
        System.out.println("data2: " + data2.count);

        StaticVar data3 = new StaticVar("C");
        System.out.println("data3: " + data3.count);

        System.out.println("정적메서드 호출");
        StaticMethod.staticCall();

        System.out.println("인스턴스 메서드 호출1");
        StaticMethod staticMethod1 = new StaticMethod();
        staticMethod1.instanceCall();

        System.out.println("인스턴스 메서드 호출2");
        StaticMethod staticMethod2 = new StaticMethod();
        staticMethod2.instanceCall();
    }
}
