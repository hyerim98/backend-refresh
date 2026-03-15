package hello.core.section3_basic.discount;

import hello.core.section3_basic.member.Grade;
import hello.core.section3_basic.member.Member;

public class RateDiscountPolicyImpl implements DiscountPolicy{
    private int discountPercent = 10; // 10% 할인
    @Override
    public int discount(Member member, int price) {
        if(Grade.VIP == member.getGrade()) {
            return price * discountPercent / 100;
        }
        return 0;
    }
}
