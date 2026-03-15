package hello.core.section8_autowired.order;

import hello.core.section8_autowired.discount.DiscountPolicy;
import hello.core.section8_autowired.member.Member;
import hello.core.section8_autowired.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        return new Order(memberId, itemName, itemPrice, discountPolicy.discount(member, itemPrice));
    }
}
