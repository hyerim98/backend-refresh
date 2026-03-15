package hello.core.section7_componentscan.discount;

import hello.core.section7_componentscan.member.Grade;
import hello.core.section7_componentscan.member.Member;
import org.springframework.stereotype.Component;

@Component
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
