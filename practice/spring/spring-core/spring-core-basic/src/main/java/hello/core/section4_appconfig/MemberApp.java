package hello.core.section4_appconfig;

import hello.core.section4_appconfig.member.Grade;
import hello.core.section4_appconfig.member.Member;
import hello.core.section4_appconfig.member.MemberService;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member : " + member.getName());
        System.out.println("find Member : " + findMember.getName());
    }
}
