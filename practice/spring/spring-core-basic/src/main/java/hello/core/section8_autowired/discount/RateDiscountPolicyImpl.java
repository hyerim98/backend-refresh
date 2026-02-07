package hello.core.section8_autowired.discount;

import hello.core.section8_autowired.member.Grade;
import hello.core.section8_autowired.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RateDiscountPolicyImpl implements DiscountPolicy {
    private int discountPercent = 10; // 10% 할인
    @Override
    public int discount(Member member, int price) {
        if(Grade.VIP == member.getGrade()) {
            return price * discountPercent / 100;
        }
        return 0;
    }
}
