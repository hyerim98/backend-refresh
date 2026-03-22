# Section5 - 스프링과 문제 해결 (트랜잭션)

## 기존 트랜잭션 방식의 문제

JDBC를 사용한 트랜잭션 처리

```
Connection 획득
 ↓
autoCommit(false)
 ↓
비즈니스 로직 실행
 ↓
commit / rollback
 ↓
Connection 종료
```

문제점

- 트랜잭션 코드가 Service 계층에 포함됨
- 중복 코드 발생
- 유지보수 어려움
- DB 기술에 의존적

---

## 트랜잭션 추상화

Spring은 트랜잭션 처리를 추상화한다.

```
PlatformTransactionManager
```

---

## PlatformTransactionManager

Spring의 트랜잭션 인터페이스

역할

- 트랜잭션 시작
- commit
- rollback

---

## 트랜잭션 매니저 구조

```
Application
   ↓
TransactionManager
   ↓
DataSource
   ↓
Database
```

구현체 예

- DataSourceTransactionManager (JDBC)
- JpaTransactionManager (JPA)

---

## 트랜잭션 동기화

Spring은 트랜잭션 동안 같은 Connection을 사용하도록 보장한다.

```
TransactionSynchronizationManager
```

---

## 동작 방식

```
1. 트랜잭션 시작
2. Connection 획득
3. ThreadLocal에 저장
4. Repository에서 같은 Connection 사용
5. 트랜잭션 종료 시 Connection 반환
```

---

## 트랜잭션 처리 로직 ⭐⭐⭐⭐⭐

Spring 내부에서 수행되는 흐름

```
1. 프록시 호출
- @Transactional이 붙은 메서드를 프록시가 가로챔

2. 트랜잭션 매니저 획득
- PlatformTransactionManager 조회

3. 트랜잭션 시작 (getTransaction())
- 트랜잭션 동기화 활성화
- 커넥션 획득
- autoCommit = false 설정
- TransactionSynchronizationManager에 커넥션 바인딩

4. 실제 서비스 로직 호출

5. 서비스 내부에서 커넥션 사용
- 새로운 커넥션 생성 ❌
- TransactionSynchronizationManager에서 기존 커넥션 조회 후 재사용
- (예: DataSourceUtils.getConnection())

6. 트랜잭션 종료
- 성공 시 commit
- 실패 시 rollback

7. 정리 단계
- TransactionSynchronizationManager에서 커넥션 unbind
- autoCommit = true로 복구
- 커넥션 반환 (close → 커넥션 풀로 반환)
```

---

## 트랜잭션 AOP

Spring은 AOP를 사용해 트랜잭션을 적용한다.

```
프록시 패턴
```

---

## @Transactional

Spring에서 트랜잭션을 선언적으로 적용하는 방법

```
@Service
@Transactional
public class OrderService {
}
```

---

## @Transactional 특징

- 메서드 단위로 트랜잭션 적용
- 런타임 예외(언체크 예외) 발생 시 rollback
- 체크 예외는 rollback 안됨 (기본)

---

## 트랜잭션 적용 위치 ⭐

```
Controller ❌
Repository ❌
Service ⭕
```

이유

```
비즈니스 로직 단위로 트랜잭션을 묶어야 하기 때문
```

---

## 트랜잭션 핵심 구조

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

## DB1 전체 흐름 연결

```
JDBC
 ↓
Connection Pool
 ↓
DataSource
 ↓
Transaction
 ↓
TransactionSynchronizationManager
 ↓
@Transactional
```

---

## 핵심 정리 ⭐⭐⭐⭐⭐

```
Spring은 트랜잭션을 AOP로 처리하여
비즈니스 로직과 트랜잭션 로직을 분리한다
```