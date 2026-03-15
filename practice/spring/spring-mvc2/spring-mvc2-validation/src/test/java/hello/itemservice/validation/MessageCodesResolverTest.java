package hello.itemservice.validation;

import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

public class MessageCodesResolverTest {
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    /**
     * 객체 오류의 경우 다음 순서로 2가지 생성
     * 1.: code + "." + object name
     * 2.: code
     * 예) 오류 코드: required, object name: item
     * 1.: required.item
     * 2.: required
     */
    @Test
    void messageCodesResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        for (String messageCode : messageCodes) {
            System.out.println("messageCode : " + messageCode);
            /**
             * messageCode : required.item
             * messageCode : required
             */
        }
    }

    /**
     * 필드 오류의 경우 다음 순서로 4가지 메시지 코드 생성
     * 1.: code + "." + object name + "." + field
     * 2.: code + "." + field
     * 3.: code + "." + field type
     * 4.: code
     * 예) 오류 코드: typeMismatch, object name "user", field "age", field type: int
     * 1. "typeMismatch.user.age"
     * 2. "typeMismatch.age"
     * 3. "typeMismatch.int"
     * 4. "typeMismatch"
     */
    @Test
    void messageCodesResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode : " + messageCode);
            /**
             * messageCode : required.item.itemName
             * messageCode : required.itemName
             * messageCode : required.java.lang.String
             * messageCode : required
             */
        }
    }
}
