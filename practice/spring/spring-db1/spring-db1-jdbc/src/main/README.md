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