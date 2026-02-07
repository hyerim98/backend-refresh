package hello.core.section8_autowired.discount;

import hello.core.section8_autowired.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
