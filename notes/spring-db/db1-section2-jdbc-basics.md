# Section2 - JDBC 이해

## JDBC(Java Database Connectivity)

JDBC는 자바에서 데이터베이스에 접근하기 위한 **표준 API**이다.

애플리케이션이 특정 DB 기술에 종속되지 않도록  
**공통 인터페이스**를 제공한다.

즉, 동일한 JDBC API를 사용하면  
다양한 데이터베이스(MySQL, H2, Oracle 등)를 사용할 수 있다.

---

## JDBC 등장 배경

초기에는 데이터베이스마다 접근 방식이 달랐다.

예

```
애플리케이션
   ↓
MySQL API
```

```
애플리케이션
   ↓
Oracle API
```

이 경우 문제점

- 데이터베이스가 변경되면 애플리케이션 코드 수정 필요
- DB 기술 종속 발생

그래서 등장한 것이 **JDBC 표준 인터페이스**이다.

```
애플리케이션
   ↓
JDBC 인터페이스
   ↓
JDBC Driver
   ↓
Database
```

---

## JDBC 구조

```
Application
   ↓
JDBC API
   ↓
JDBC Driver
   ↓
Database
```

### JDBC Driver

각 데이터베이스 벤더가 제공하는 구현체

예

- MySQL Driver
- H2 Driver
- Oracle Driver

JDBC 인터페이스를 구현하여  
DB와 실제 통신을 담당한다.

---

## JDBC 주요 인터페이스

JDBC는 다음 핵심 인터페이스로 구성된다.

### Connection

DB와 연결을 담당

```
애플리케이션
   ↓
Connection
   ↓
Database
```

역할

- DB 연결 생성
- 트랜잭션 관리

---

### Statement

SQL 실행을 담당

```
Connection
   ↓
Statement
   ↓
SQL 실행
```

SQL을 데이터베이스에 전달한다.

---

### ResultSet

조회 결과를 담는 객체

```
SELECT 결과
   ↓
ResultSet
```

특징

- DB 결과 데이터를 행 단위로 조회
- 커서를 이동하면서 데이터 접근

---

## JDBC 동작 흐름

JDBC를 사용한 데이터 접근 과정

```
1. Connection 획득
2. SQL 실행 객체 생성 (Statement)
3. SQL 실행
4. 결과 조회 (ResultSet)
5. 자원 정리
```

흐름

```
Application
   ↓
Connection 획득
   ↓
Statement 생성
   ↓
SQL 실행
   ↓
ResultSet 조회
   ↓
Connection 종료
```

---

## JDBC 사용 시 문제점

JDBC는 표준 API지만  
직접 사용할 경우 다음 문제가 있다.

### 1. 반복 코드 발생

매번 작성해야 하는 코드

```
Connection 생성
Statement 생성
SQL 실행
ResultSet 처리
Connection 종료
```

---

### 2. 자원 관리 문제

다음 자원을 반드시 종료해야 한다.

- Connection
- Statement
- ResultSet

종료하지 않으면 **DB 커넥션 누수 발생**

---

### 3. 예외 처리 복잡

DB 작업 중 다양한 예외가 발생할 수 있다.

- SQL 오류
- Connection 오류
- DB 연결 실패

예외 처리 코드가 복잡해진다.

---

## JDBC의 한계

JDBC는 DB 접근을 위한 표준 API지만  
직접 사용하기에는 다음 한계가 있다.

- 반복 코드가 많다
- 자원 관리가 어렵다
- 예외 처리가 복잡하다

그래서 Spring에서는 다음 기술을 제공한다.

- JdbcTemplate
- DataSource
- Transaction 관리