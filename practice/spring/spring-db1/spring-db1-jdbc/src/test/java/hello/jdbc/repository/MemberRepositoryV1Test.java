package hello.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.connection.ConnectionConst;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class MemberRepositoryV1Test {

    MemberRepositoryV1 repository;

    @BeforeEach
    void beforeEach() throws Exception {
        // 기본 DriverManager : 항상 새로운 커넥션 획득(성능 저하)
        //DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);

        // 커넥션 풀링 사용 :  HikariProxyConnection -> JdbcConnection
        // 로그를 보면 conn0 커넥션이 계속 재사용 된 것을 확인할 수 있음
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        repository = new MemberRepositoryV1(dataSource);
    }
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