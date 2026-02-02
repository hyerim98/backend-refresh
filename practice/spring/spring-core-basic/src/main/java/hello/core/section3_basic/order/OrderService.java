package hello.core.section3_basic.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
