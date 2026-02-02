package hello.core.section4_appconfig;

import hello.core.section4_appconfig.discount.DiscountPolicy;
import hello.core.section4_appconfig.discount.RateDiscountPolicyImpl;
import hello.core.section4_appconfig.member.MemberRepository;
import hello.core.section4_appconfig.member.MemberService;
import hello.core.section4_appconfig.member.MemberServiceImpl;
import hello.core.section4_appconfig.member.MemoryMemberRepository;
import hello.core.section4_appconfig.order.OrderService;
import hello.core.section4_appconfig.order.OrderServiceImpl;

public class AppConfig {
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicyImpl();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
