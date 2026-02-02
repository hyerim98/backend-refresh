package hello.core.section3_basic;

import hello.core.section3_basic.member.Grade;
import hello.core.section3_basic.member.Member;
import hello.core.section3_basic.member.MemberService;
import hello.core.section3_basic.member.MemberServiceImpl;
import hello.core.section3_basic.order.Order;
import hello.core.section3_basic.order.OrderService;
import hello.core.section3_basic.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);

    }
}
