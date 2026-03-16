package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();
    @Test
    void save() throws SQLException {
        Member member = new Member("memberV0", 10000);
        repository.save(member);
    }

    @Test
    void crud() throws SQLException {
        // findById
        Member findMember = repository.findById("memberV0");
        log.info("findMember={}", findMember);
        assertThat(findMember.getMemberId()).isEqualTo("memberV0");

        // update : money: 10000 -> 20000
        repository.update(findMember.getMemberId(), 20000);
        Member updateMember = repository.findById(findMember.getMemberId());
        assertThat(updateMember.getMoney()).isEqualTo(20000);

        repository.delete(updateMember.getMemberId());
        assertThatThrownBy(() -> repository.findById(updateMember.getMemberId())).isInstanceOf(NoSuchElementException.class);
    }
}