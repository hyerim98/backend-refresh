package hello.core.section7_componentscan.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
