package hello.core.section3_basic.member;

import hello.core.section3_basic.member.Grade;
import hello.core.section3_basic.member.Member;
import hello.core.section3_basic.member.MemberService;
import hello.core.section3_basic.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService = new MemberServiceImpl();
    @Test
    void join() {
        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
