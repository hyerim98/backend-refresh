package hello.core.section4_appconfig.discount;

import hello.core.section4_appconfig.member.Grade;
import hello.core.section4_appconfig.member.Member;

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
