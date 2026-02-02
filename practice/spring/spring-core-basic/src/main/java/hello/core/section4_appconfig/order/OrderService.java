package hello.core.section4_appconfig.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
