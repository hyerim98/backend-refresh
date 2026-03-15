package hello.core.section5_spring.order;

import hello.core.section5_spring.discount.DiscountPolicy;
import hello.core.section5_spring.member.Member;
import hello.core.section5_spring.member.MemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        return new Order(memberId, itemName, itemPrice, discountPolicy.discount(member, itemPrice));
    }
}
