package hello.core.section3_basic.discount;

import hello.core.section3_basic.member.Grade;
import hello.core.section3_basic.member.Member;

public class FixDiscountPolicyImpl implements DiscountPolicy{
    private int discountFixAmount = 1000;
    @Override
    public int discount(Member member, int price) {
        if(Grade.VIP == member.getGrade()) {
            return discountFixAmount;
        }
        return 0;
    }
}
