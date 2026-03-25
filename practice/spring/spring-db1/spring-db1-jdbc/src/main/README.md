# Section2 - JDBC 이해

JDBC를 사용하여  
애플리케이션이 데이터베이스에 접근하는 전체 흐름을 이해한다.

---

## JDBC 기본 흐름

데이터베이스 접근 과정

```
Application
   ↓
JDBC API
   ↓
JDBC Driver
   ↓
Database
```

JDBC Driver는  
각 DB 벤더가 제공하는 구현체이다.

---

## JDBC 데이터 접근 흐름

데이터 조회 과정

```
Application
   ↓
Connection 생성
   ↓
Statement 생성
   ↓
SQL 실행
   ↓
ResultSet 반환
   ↓
데이터 조회
   ↓
Connection 종료
```

---

## JDBC 핵심 객체 역할

### Connection

DB와 연결을 담당

```
Application
   ↓
Connection
```

---

### Statement

SQL 실행 담당

```
Connection
   ↓
Statement
   ↓
SQL 실행
```

---

### ResultSet

조회 결과 데이터 저장

```
Database
   ↓
ResultSet
```

행 단위로 데이터를 조회한다.

---

## JDBC 사용 시 문제점

직접 JDBC를 사용하면 다음 문제가 발생한다.

### 반복 코드

매번 동일한 코드 작성 필요

```
Connection 생성
Statement 생성
SQL 실행
ResultSet 처리
Connection 종료
```

---

### 자원 관리 문제

다음 자원을 반드시 종료해야 한다.

```
Connection
Statement
ResultSet
```

종료하지 않으면 **Connection 누수 발생**

---

### 예외 처리 복잡

DB 작업 시 다양한 예외 발생

```
SQL Exception
Connection 오류
DB 연결 실패
```

예외 처리 코드가 복잡해진다.

---

## JDBC 이후 발전

이러한 문제를 해결하기 위해 등장한 기술

```
Connection Pool
DataSource
Spring JDBC
Spring Transaction
```

이후 강의에서  
JDBC의 문제를 단계적으로 해결하는 과정을 학습한다.



# Section3 - 커넥션 풀과 데이터소스

DB 접근 구조를 개선하는 과정을 흐름으로 이해한다.

---

## v1 - DriverManager 사용

Connection을 직접 생성하는 구조

```
Application
   ↓
DriverManager
   ↓
Connection
   ↓
Database
```

문제점

- 매 요청마다 Connection 생성
- 성능 저하
- 리소스 낭비

---

## v2 - 커넥션 풀 도입

Connection을 미리 생성하고 재사용

```
Application
   ↓
Connection Pool
   ↓
Database
```

흐름

```
요청
 ↓
Connection Pool에서 Connection 획득
 ↓
DB 작업
 ↓
Connection 반환
```

개선점

- 응답 속도 향상
- Connection 생성 비용 감소

---

## v3 - DataSource 적용

Connection 획득 방식을 추상화

```
Application
   ↓
DataSource
   ↓
Connection Pool
   ↓
Database
```

핵심 변화

```
DriverManager → DataSource
```

---

## 구조 변화 정리

### before

```
Application
   ↓
DriverManager
   ↓
Connection
```

---

### after

```
Application
   ↓
DataSource
   ↓
Connection Pool
   ↓
Connection
```

---

## 핵심 흐름

DB 접근 방식의 발전

```
DriverManager
 ↓
Connection Pool
 ↓
DataSource
```

---

## 🚨 중요 포인트

### 1. Connection 재사용

```
매번 생성 ❌
Pool에서 재사용 ⭕
```

---

### 2. Connection 반환 필수

```
사용 후 반드시 반환해야 한다.
```

---

### 3. DataSource 사용 이유

```
Connection 생성 로직 분리
```

---

## 다음 단계 연결

이 구조는 이후 트랜잭션과 연결된다.

```
DataSource
 ↓
Connection
 ↓
Transaction
 ↓
@Transactional
```

즉, 이 섹션은

```
Connection 관리 구조 이해
```

가 핵심이다.



# Section4 - 트랜잭션 이해

JDBC를 사용하여 트랜잭션을 직접 제어하는 흐름을 이해한다.

---

## v1 - 트랜잭션 미적용

각 SQL이 독립적으로 실행된다.

```
Service
 ↓
Repository
 ↓
DB
```

문제

```
중간 실패 시 데이터 불일치 발생
```

---

## v2 - JDBC 트랜잭션 적용

Connection을 사용하여 트랜잭션을 직접 제어

```
Service
 ↓
Connection 획득
 ↓
autoCommit(false)
 ↓
Repository
 ↓
commit / rollback
 ↓
Connection 종료
```

---

## 트랜잭션 처리 흐름(서비스 계층)

```
1. Connection 생성
2. autoCommit(false)
3. 비즈니스 로직 실행
4. 성공 → commit
5. 실패 → rollback
6. Connection 종료
```

---

## 핵심 포인트

### 1. 자동 커밋 OFF

```
autoCommit(false)
```

→ 트랜잭션 시작

---

### 2. commit / rollback

```
성공 → commit
실패 → rollback
```

---

### 3. 트랜잭션 범위

```
비즈니스 로직 단위로 설정
```

---

## 문제점

JDBC 방식은 다음과 같은 문제가 있다.

```
트랜잭션 코드가 Service에 포함됨
중복 코드 발생
유지보수 어려움
```

---

## 다음 단계 연결

이 문제를 해결하기 위해

```
트랜잭션 동기화
Spring 트랜잭션
```

이 등장한다.


# Section5 - 스프링과 문제 해결 (트랜잭션)

Spring을 사용하여 트랜잭션 문제를 해결하는 과정을 단계적으로 이해한다.

---

## v1 - JDBC 트랜잭션

```
Service
 ↓
Connection 획득
 ↓
autoCommit(false)
 ↓
비즈니스 로직
 ↓
commit / rollback
```

문제

- Service에 트랜잭션 코드 포함
- 중복 코드 발생

---

## v2 - 트랜잭션 매니저 도입

```
Service
 ↓
TransactionManager
 ↓
Repository
 ↓
DB
```

개선점

```
트랜잭션 로직 분리
```

---

## v3 - 트랜잭션 동기화 적용

```
TransactionSynchronizationManager
```

동작

```
Service → Connection 생성 및 저장
Repository → 같은 Connection 사용
```

---

## v4 - @Transactional 적용

```
@Service
@Transactional
public class Service
```

구조

```
Controller
 ↓
Service (@Transactional)
 ↓
Repository
 ↓
DB
```

---

## 트랜잭션 처리 전체 흐름 ⭐⭐⭐⭐⭐

```
@Transactional
 ↓
프록시 객체 생성
 ↓
트랜잭션 시작
 ↓
Connection 획득
 ↓
ThreadLocal 저장
 ↓
Service 로직 실행
 ↓
Repository 호출
 ↓
DB 작업 수행
 ↓
성공 → commit
실패 → rollback
 ↓
트랜잭션 종료
 ↓
Connection 반환
```

---

## 내부 동작 흐름

```
1. 프록시가 Service 호출 가로챔
2. 트랜잭션 시작
3. Connection 생성 및 저장
4. 실제 Service 로직 실행
5. 예외 여부 확인
6. commit 또는 rollback
7. Connection 반환
```

---

## 핵심 포인트

### 1. 트랜잭션 시작 위치

```
Service 계층
```

---

### 2. 트랜잭션 처리 방식

```
AOP 기반 프록시
```

---

### 3. Connection 관리

```
Spring이 자동으로 관리
```

---

## 🚨 중요 정리

```
비즈니스 로직과 트랜잭션 로직 분리
@Transactional = 트랜잭션 처리 자동화 + AOP 기반 처리
```

---

## 한 줄 정리

```
@Transactional을 사용하면 트랜잭션을 자동으로 처리할 수 있다
```


# 섹션6. 자바 예외 이해

## 1. Checked Exception 실습
- Repository.run()에서 MyCheckedException 발생
- Service.call()에서 try/catch로 처리
- 처리 강제되는 것을 확인

## 2. Unchecked Exception 실습
- Repository.run()에서 MyUncheckedException 발생
- Service.call()에서는 try/catch 없이 동작
- 호출 계층을 타고 자동 전파되는 것 확인

## 3. 예외 변환 실습
- SQLException을 잡아서 MyDbException으로 변환
- 변환 시 원인(cause)을 꼭 포함
- 예: new MyDbException("DB 오류", e)
```
try {
      run();
    }
    catch (SQLException e) {
      throw new RuntimeSQLException("DB오류", e); // 중요!
    }
```

## 4. cause 보존 테스트
- MyDbException.getCause()가 SQLException인지 확인하는 테스트 작성
- 원인 보존의 중요성 이해
```
public void call() {
    try {
        runSQL();
    }
    catch (SQLException e) {
        throw new RuntimeSQLException(e); //기존 예외(e) 포함 필수!!
    }
}
```

## 5. Checked vs Unchecked 비교 정리
- Checked는 throws 체인 누적
- Unchecked는 코드가 깔끔하지만 예외 발생 가능성이 코드에서 드러나지 않음(**보통 언체크 예외 사용**)


# 섹션7. 스프링과 문제 해결 - 예외 처리 반복

## 1. 기존 JDBC 예외 처리 반복 확인 실습
- try/catch로 SQLException 처리
- finally에서 리소스 정리
- 동일한 패턴이 모든 Repository 메서드에 반복됨 확인

---

## 2. DataAccessException 변환 실습
- SimpleJdbcInsert OR JdbcTemplate 기반 Repository 작성
- SQLException 발생 시 스프링이 자동으로 적절한 예외로 전환하는 과정 확인

예:
- 중복 키 발생 → DuplicateKeyException
- SQL 문법 오류 → BadSqlGrammarException

---

## 3. SQLExceptionTranslator 직접 사용해보기
- translator = new SQLErrorCodeSQLExceptionTranslator()
- translator.translate() 결과 로그 출력
- DB errorCode에 따라 어떤 예외로 변환되는지 실습

---

## 4. 예외 원인(cause) 유지 확인 테스트
- 변환된 스프링 예외의 cause가 SQLException인지 확인
- 원인 유지가 정상적으로 되는지 검증

---

## 5. DuplicateKeyException 처리 실습
- save() 과정에서 중복 키 발생 유도
- catch(DuplicateKeyException e)로 로직 처리
  → 예: 재시도 전략 또는 대체 키 사용 등

---

## 6. 스프링 예외 계층 탐색 실습
- 발생한 예외의 클래스 계층 출력
- DataAccessException이 상위 추상화임을 확인

---

## 7. Repository 계층에서 기술 의존성 제거 확인
- Repository 코드에서 SQLException, try/catch가 사라진 구조 실습
- 코드가 매우 간결해지고 테스트하기 쉬워짐

---

## 8. 전체 흐름 이해 실습
1) SQL 실행
2) SQLException 발생
3) 스프링이 errorCode 분석
4) 적절한 DataAccessException으로 변환
5) 서비스·컨트롤러는 도메인 로직에 집중

위 흐름을 로그로 확인해보며 정리
