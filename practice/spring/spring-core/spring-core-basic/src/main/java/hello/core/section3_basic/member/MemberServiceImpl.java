package hello.core.section3_basic.member;

public class MemberServiceImpl implements MemberService{
    // 구현체 의존 DIP 위반
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id);
    }
}
