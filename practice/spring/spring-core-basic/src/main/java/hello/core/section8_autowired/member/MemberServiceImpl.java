package hello.core.section8_autowired.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    // 구현체 의존 DIP 위반
    private final MemberRepository memberRepository;
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id);
    }
}
