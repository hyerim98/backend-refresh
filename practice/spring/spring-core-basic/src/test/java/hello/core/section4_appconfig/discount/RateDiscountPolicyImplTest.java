package hello.core.section4_appconfig.discount;

import hello.core.section4_appconfig.AppConfig;
import hello.core.section4_appconfig.discount.DiscountPolicy;
import hello.core.section4_appconfig.discount.RateDiscountPolicyImpl;
import hello.core.section4_appconfig.member.Grade;
import hello.core.section4_appconfig.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RateDiscountPolicyImplTest {

    DiscountPolicy discountPolicy;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        discountPolicy = appConfig.discountPolicy();
    }

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        Member member = new Member(1L, "memberA", Grade.VIP);
        int discount = discountPolicy.discount(member, 20000);
        Assertions.assertThat(discount).isEqualTo(2000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() {
        Member member = new Member(1L, "memberA", Grade.BASIC);
        int discount = discountPolicy.discount(member, 10000);
        Assertions.assertThat(discount).isEqualTo(0);
    }
}