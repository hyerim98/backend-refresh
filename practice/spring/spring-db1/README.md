# 🌐 Spring DB1 - 데이터 접근 핵심 원리

김영한님의  
Spring DB 1편 - 데이터 접근 핵심 원리 강의를 학습하며  
데이터 접근 기술과 트랜잭션 처리의 핵심 원리를 정리했습니다.

---

## 📚 Study Structure
- **notes** : 개념 및 동작 원리 정리
- **practice** : 코드 기반 데이터 접근 기술 적용 흐름 정리

---

## 📘 notes (개념 정리)

데이터베이스 접근 기술과  
Spring이 제공하는 추상화 구조를 정리했습니다.

---

### 1. JDBC 이해
Java에서 DB에 접근하기 위한 기본 기술

- JDBC API 개념
- DB 연결 구조
- SQL 실행 과정

**기본 흐름**
```
Connection → Statement → ResultSet
```

**한계**
- 반복 코드 과다
- 예외 처리 번거로움
- 리소스 관리 필요

---

### 2. 커넥션과 DataSource

DB 연결을 효율적으로 관리하는 방법

- DriverManager 방식
- DataSource 방식

**핵심 개념**

- 커넥션 풀
- 성능 최적화
- 설정 분리

---

### 3. 트랜잭션 이해

데이터 정합성을 보장하는 핵심 개념

**ACID**

- Atomicity (원자성)
- Consistency (일관성)
- Isolation (격리성)
- Durability (지속성)

**트랜잭션 처리 흐름**
```
autoCommit(false)
→ 비즈니스 로직 실행
→ commit / rollback
```

---

### 4. 스프링과 트랜잭션

Spring이 제공하는 트랜잭션 관리

- @Transactional
- AOP 기반 처리
- 프록시 패턴

**특징**

- 선언적 트랜잭션 관리
- 런타임 예외(언체크 예외) 발생 시 rollback

---

### 5. 예외 처리와 스프링 예외 추상화

DB 기술에 독립적인 예외 처리

- SQLException 문제
- DataAccessException 제공

**장점**

- DB 변경 시 영향 최소화
- 일관된 예외 처리 가능

---

### 6. JdbcTemplate

JDBC의 반복 코드를 제거한 템플릿

**제공 기능**

- 리소스 자동 반환
- 예외 처리 자동화
- SQL 실행 간소화

---

## 📗 practice (구현 정리)

강의에서 학습한 데이터 접근 방식을  
단계별로 발전시키며 정리했습니다.

---

### v1 - 순수 JDBC

가장 기본적인 DB 접근 방식
```
Connection
↓
Statement
↓
ResultSet
```


**특징**
- 모든 코드 직접 작성
- 리소스 관리 필요

---

### v2 - DataSource 적용

커넥션 관리 개선
```
Client
↓
DataSource
↓
Connection
```


**개선점**
- 커넥션 재사용
- 성능 향상

---

### v3 - 트랜잭션 적용

트랜잭션 수동 관리
```
Connection
↓
setAutoCommit(false)
↓
commit / rollback
```


**문제점**
- 비즈니스 로직과 기술 로직 혼재

---

### v4 - 트랜잭션 분리

트랜잭션 로직 분리

- 서비스 계층 도입
- 트랜잭션 책임 분리

---

### v5 - @Transactional 적용

Spring 기반 선언적 트랜잭션
```
Service (@Transactional)
↓
Repository
```

**개선점**
- 코드 간결화
- 유지보수성 향상

---

### v6 - JdbcTemplate 적용

JDBC 추상화 적용
```
Service
↓
Repository (JdbcTemplate)
↓
DB
```

**개선점**
- 반복 코드 제거
- 생산성 향상

---

## 🎯 Study Goal

- JDBC 기반 데이터 접근 이해
- 커넥션 관리 구조 이해
- 트랜잭션 동작 원리 이해
- Spring 트랜잭션 추상화 이해
- JdbcTemplate 활용 능력 확보  