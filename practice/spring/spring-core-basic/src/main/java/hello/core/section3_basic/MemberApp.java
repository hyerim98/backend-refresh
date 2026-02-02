package hello.core.section3_basic;

import hello.core.section3_basic.member.Grade;
import hello.core.section3_basic.member.Member;
import hello.core.section3_basic.member.MemberService;
import hello.core.section3_basic.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member : " + member.getName());
        System.out.println("find Member : " + findMember.getName());
    }
}
