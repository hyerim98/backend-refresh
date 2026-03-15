package hello.core.section5_spring;

import hello.core.section5_spring.discount.DiscountPolicy;
import hello.core.section5_spring.discount.RateDiscountPolicyImpl;
import hello.core.section5_spring.member.MemberRepository;
import hello.core.section5_spring.member.MemberService;
import hello.core.section5_spring.member.MemberServiceImpl;
import hello.core.section5_spring.member.MemoryMemberRepository;
import hello.core.section5_spring.order.OrderService;
import hello.core.section5_spring.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정을 구성한다는 어노테이션
public class AppConfig {
    @Bean // 스프링 컨테이너에 스프링 빈으로 등록
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicyImpl();
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
