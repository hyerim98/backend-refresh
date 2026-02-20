package hello.itemservice.domain.item;

import lombok.Data;

// 도메인에서는 대부분 Long, Integer 사용(NULL 상태 표현을 위해)
@Data
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
