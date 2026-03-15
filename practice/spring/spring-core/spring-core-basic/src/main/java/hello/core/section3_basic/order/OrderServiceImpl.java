package hello.core.section3_basic.order;

import hello.core.section3_basic.discount.DiscountPolicy;
import hello.core.section3_basic.discount.FixDiscountPolicyImpl;
import hello.core.section3_basic.member.Member;
import hello.core.section3_basic.member.MemberRepository;
import hello.core.section3_basic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicyImpl(); // FixDiscountPolicyImpl, RateDiscountPolicyImpl 이렇게 2개의 구현체에 클라이언트 코드가 의존하는 문제(DIP, OCP 위반)

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        return new Order(memberId, itemName, itemPrice, discountPolicy.discount(member, itemPrice));
    }
}
