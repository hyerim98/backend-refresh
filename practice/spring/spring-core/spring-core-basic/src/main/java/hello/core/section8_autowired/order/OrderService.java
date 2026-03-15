package hello.core.section8_autowired.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
