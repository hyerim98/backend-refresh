package hello.core.section4_appconfig.order;

import hello.core.section4_appconfig.AppConfig;
import hello.core.section4_appconfig.member.Grade;
import hello.core.section4_appconfig.member.Member;
import hello.core.section4_appconfig.member.MemberService;
import hello.core.section4_appconfig.member.MemberServiceImpl;
import hello.core.section4_appconfig.order.Order;
import hello.core.section4_appconfig.order.OrderService;
import hello.core.section4_appconfig.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
