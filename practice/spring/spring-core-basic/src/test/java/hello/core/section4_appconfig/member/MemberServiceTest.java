package hello.core.section4_appconfig.member;

import hello.core.section4_appconfig.AppConfig;
import hello.core.section4_appconfig.member.Grade;
import hello.core.section4_appconfig.member.Member;
import hello.core.section4_appconfig.member.MemberService;
import hello.core.section4_appconfig.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
