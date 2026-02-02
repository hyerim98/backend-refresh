package hello.core.section3_basic.discount;

import hello.core.section3_basic.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
