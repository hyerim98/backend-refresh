package hello.core.section7_componentscan.discount;

import hello.core.section7_componentscan.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
