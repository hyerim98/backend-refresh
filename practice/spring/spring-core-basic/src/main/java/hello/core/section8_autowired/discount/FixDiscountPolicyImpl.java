package hello.core.section8_autowired.discount;

import hello.core.section8_autowired.member.Grade;
import hello.core.section8_autowired.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicyImpl implements DiscountPolicy {
    private int discountFixAmount = 1000;
    @Override
    public int discount(Member member, int price) {
        if(Grade.VIP == member.getGrade()) {
            return discountFixAmount;
        }
        return 0;
    }
}
