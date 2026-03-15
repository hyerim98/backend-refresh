package hello.core.section7_componentscan.member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long id);
}
