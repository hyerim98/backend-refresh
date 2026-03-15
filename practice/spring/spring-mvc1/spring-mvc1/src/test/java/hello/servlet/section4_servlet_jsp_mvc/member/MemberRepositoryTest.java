package hello.servlet.section4_servlet_jsp_mvc.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member("memberA", 20);
        Member saveMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(saveMember.getId());

        assertThat(saveMember).isEqualTo(findMember);
    }

    @Test
    void findAll() {
        Member member1 = new Member("memberA", 20);
        Member member2 = new Member("memberB", 25);
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(2);
    }
}