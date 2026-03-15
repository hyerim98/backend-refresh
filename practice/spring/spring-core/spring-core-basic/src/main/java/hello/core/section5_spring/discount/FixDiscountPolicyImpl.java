package hello.core.section5_spring.discount;

import hello.core.section5_spring.member.Grade;
import hello.core.section5_spring.member.Member;

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
