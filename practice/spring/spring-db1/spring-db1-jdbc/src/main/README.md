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