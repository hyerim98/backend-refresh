package hello.core.section3_basic.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long id);
}
