# Section3 - 커넥션 풀과 데이터소스 이해

## 커넥션(Connection) 문제

JDBC를 사용할 때 매번 DB 연결을 생성하는 것은 매우 비효율적이다.

```
Connection 생성
 ↓
SQL 실행
 ↓
Connection 종료
```

문제점

- Connection 생성 비용이 큼
- DB 연결 시간이 오래 걸림
- 서버 리소스 낭비

---

## 커넥션 풀(Connection Pool)

Connection을 미리 생성해두고 재사용하는 방식

```
Application
   ↓
Connection Pool
   ↓
Database
```

동작 방식

```
1. 애플리케이션 시작 시 Connection 미리 생성
2. Pool에 Connection 저장
3. 요청 시 Connection 반환
4. 사용 후 다시 Pool에 반환
```

---

## 커넥션 풀의 장점

- 빠른 응답 속도
- DB 연결 비용 감소
- 리소스 효율적 사용

---

## 커넥션 풀 주의사항 ⭐

```
Connection을 사용 후 반드시 반환해야 한다.
```

반환하지 않으면

```
Connection 누수 발생
 → Pool 고갈
 → 장애 발생
```

---

## DataSource

Connection을 획득하는 방법을 추상화한 인터페이스

```
Application
   ↓
DataSource
   ↓
Connection Pool
   ↓
Database
```

---

## DataSource 도입 이유

기존

```
DriverManager.getConnection()
```

문제

- Connection 생성 방식이 코드에 직접 들어감
- 변경 시 코드 수정 필요

---

## DataSource 적용

```
Application
   ↓
DataSource.getConnection()
```

장점

- Connection 생성 로직 분리
- 커넥션 풀 적용 가능
- 유연한 구조

---

## DriverManager vs DataSource

### DriverManager

- Connection을 직접 생성
- 커넥션 풀 사용 불가
- 확장성 낮음

---

### DataSource

- Connection 생성 로직 추상화
- 커넥션 풀 사용 가능
- 확장성 높음

---

## 커넥션 풀 구현체

대표적인 구현체(DataSource의 구현체)

- HikariCP (Spring Boot 기본)
- Apache DBCP
- Tomcat JDBC Pool

---

## Spring과 커넥션 풀

Spring Boot는 기본적으로  
HikariCP를 커넥션 풀로 사용한다.

---

## DB1 핵심 포인트 ⭐⭐⭐⭐⭐

이 섹션의 핵심은 이것이다.

```
Connection 직접 생성 → 비효율
 ↓
Connection Pool → 성능 개선
 ↓
DataSource → 추상화
```

즉

```
Connection 관리 문제 해결
```

이게 핵심이다.