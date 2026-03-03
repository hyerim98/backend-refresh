package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
@Slf4j
@Repository
public class MemberRepository {
    private static final Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        log.info("save: member={}", member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    // Optional은 값이 없을 수도 있음을 명확하게 표현하는 타입
    public Optional<Member> findByLoginId(String loginId) {
        /*List<Member> all = findAll();
        for (Member member : all) {
            if(member.getLoginId().equals(loginId)) {
                return Optional.of(member);
            }
        }
        return Optional.empty();*/

        // 위 코드와 같은 로직
        return findAll().stream().filter(m -> m.getLoginId().equals(loginId)).findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
