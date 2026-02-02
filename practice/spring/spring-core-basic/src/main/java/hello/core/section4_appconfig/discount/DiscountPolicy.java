package hello.core.section4_appconfig.discount;

import hello.core.section4_appconfig.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
