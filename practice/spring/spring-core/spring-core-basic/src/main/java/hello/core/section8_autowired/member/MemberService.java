package hello.core.section8_autowired.member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long id);
}
