package hello.core.section6_singleton;

public class StatelessService {

    public int order(String name, int price) {
        System.out.println("name=" + name + " price=" + price);
        return price; // 필드가 공유되지 않도록 설계를 해야 함
    }

}
