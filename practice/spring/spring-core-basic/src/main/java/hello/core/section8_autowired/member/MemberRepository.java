package hello.core.section8_autowired.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long id);
}
