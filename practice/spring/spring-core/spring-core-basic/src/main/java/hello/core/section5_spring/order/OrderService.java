package hello.core.section5_spring.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
