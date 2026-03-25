package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * MemberRepositoryV4_1
     * 예외 누수 문제 해결
     * SQLException 제거
     * MemberRepository 인터페이스 의존
 * MemberRepositoryV4_2
    * SQLExceptionTranslator 사용(예외 변환기)
 * MemberRepositoryV5
    * JDBC 반복 코드 해결
 */
@Slf4j
@SpringBootTest // 스프링 컨테이너 생성(테스트에서도 사용할 수 있도록)
class MemberServiceV4Test {
    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    @Autowired // 스프링 컨테이너가 관리하는 빈들을 사용할 수 있음
    private MemberRepository memberRepository;
    @Autowired
    private MemberServiceV4 memberService;

    @AfterEach
    void after() throws SQLException {
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }

    @TestConfiguration // 테스트 안에서 내부 설정 클래스를 만들어서 사용하면서 해당 어노테이션을 붙이면 스프링 부트가 자동으로 만들어주는 빈들에 추가로 필요한 스프링 빈들을 등록하고 테스트를 수행할 수 있음
    @RequiredArgsConstructor
    static class TestConfig {
        // application.properties에 지정된 속성을 참고해서 데이터 소스와 트랜잭션 매니저를 자동으로 생성
        private final DataSource dataSource;

        // 해당 클래스에 @Repository 어노테이션이 없어서 빈 등록
        @Bean
        MemberRepository memberRepository() {
            // SQLException 제거
            //return new MemberRepositoryV4_1(dataSource);

            // 예외 변환기
            //return new MemberRepositoryV4_2(dataSource);

            // JDBC 반복 코드 해결
            return new MemberRepositoryV5(dataSource);
        }

        // 해당 클래스에 @Service 어노테이션이 없어서 빈 등록
        @Bean
        MemberServiceV4 memberServiceV4() {
            return new MemberServiceV4(memberRepository());
        }
    }

    @Test
    void AppCheck() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberRepository class={}", memberRepository.getClass());
        Assertions.assertThat(AopUtils.isAopProxy(memberService)).isTrue(); // AOP 적용대상
        Assertions.assertThat(AopUtils.isAopProxy(memberRepository)).isFalse(); // AOP 적용대상 아님
    }

    @Test
    @DisplayName("정상 이체")
    void accountTransfer(){
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_B, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        memberService.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), 2000);

        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberB = memberRepository.findById(memberB.getMemberId());
        assertThat(findMemberA.getMoney()).isEqualTo(8000);
        assertThat(findMemberB.getMoney()).isEqualTo(12000);
    }

    @Test
    @DisplayName("이체중 예외 발생")
    void accountTransferEx(){
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberEx = new Member(MEMBER_EX, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberEx);

        assertThatThrownBy(() -> memberService.accountTransfer(memberA.getMemberId(), memberEx.getMemberId(), 2000)).isInstanceOf(IllegalStateException.class);

        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberEx = memberRepository.findById(memberEx.getMemberId());
        assertThat(findMemberA.getMoney()).isEqualTo(10000);
        assertThat(findMemberEx.getMoney()).isEqualTo(10000);
    }
}