package hello.core.section5_spring.member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long id);
}
