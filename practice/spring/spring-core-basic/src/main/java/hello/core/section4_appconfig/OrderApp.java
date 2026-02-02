package hello.core.section4_appconfig;

import hello.core.section4_appconfig.member.Grade;
import hello.core.section4_appconfig.member.Member;
import hello.core.section4_appconfig.member.MemberService;
import hello.core.section4_appconfig.order.Order;
import hello.core.section4_appconfig.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
        
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);

    }
}
