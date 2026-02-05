package hello.core.section6_singleton;

public class StatefulService {
    private int price;

    public void order(String name, int price) {
        System.out.println("name=" + name + " price=" + price);
        this.price = price; // 필드에 공유 값을 설정하면 안됨
    }

    public int getPrice() {
        return price;
    }
}
