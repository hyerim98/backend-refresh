package hello.core.section4_appconfig.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long id);
}
