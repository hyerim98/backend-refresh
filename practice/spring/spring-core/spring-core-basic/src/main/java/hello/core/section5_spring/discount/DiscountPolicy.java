package hello.core.section5_spring.discount;

import hello.core.section5_spring.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
