# 섹션7. 스프링과 문제 해결 - 예외 처리 반복

## 1. 예외 처리의 반복 문제
### 공통적으로 발생하는 문제
- try/catch 반복
- 예외 변환 반복
- 예외 로깅 중복
- 컨트롤러·서비스·리포지토리 계층에 동일한 패턴 반복
- 각 계층이 기술 예외(SQL, JDBC 등)에 오염됨

### 이 문제의 본질
- “모든 계층에서 예외 처리 패턴이 동일하다”
- 예외 처리 책임이 비즈니스 로직과 섞여서 복잡도 증가

---

## 2. 해결 방향성
스프링이 제공하는 자동 예외 처리 기능 활용
- **데이터 접근 예외 변환기 (SQLExceptionTranslator)**
- **스프링 예외 추상화 (DataAccessException)**
- **AOP 기반 예외 변환**
- **트랜잭션 AOP와 예외 rollback 정책 연계**

→ 목표: 비즈니스 로직에서 예외 관련 코드 완전 제거

---

## 3. 스프링 데이터 접근 예외 계층
스프링 예외의 대표적인 특징:
- 모든 예외가 **RuntimeException 기반**
- **DataAccessException**이 최상위 추상화
- 세부 예외는 DB 종류와 무관한 논리적 분류

예:
- DuplicateKeyException
- BadSqlGrammarException
- DataIntegrityViolationException

장점:
- DB 종류가 바뀌어도 예외 클래스를 그대로 사용할 수 있음  
  (Oracle → MySQL 변경 시 SQLException 에러코드 달라져도 문제 없음)

---

## 4. SQLExceptionTranslator
스프링이 제공하는 “예외 변환기”
- JDBC ErrorCode를 분석해서 적절한 스프링 예외로 변환
- 대표 구현체: **SQLErrorCodeSQLExceptionTranslator**

작동 방식:
1. SQLException 발생
2. translator.translate("select", sql, e) 호출
3. DB ErrorCode 매핑 테이블 기반으로 스프링 예외 변환
4. 최종적으로 DataAccessException 계열 예외 반환

---

## 5. 예외 처리 반복 제거 핵심 포인트
### A. try/catch 제거
스프링 JDBC에서는 대부분 try/catch가 사라짐

### B. 예외 전환 코드 제거
스프링이 자동으로 DataAccessException으로 변환  
→ 개발자는 비즈니스 로직에만 집중

### C. 기술 예외 제거
Repository 계층이 SQLException에 더 이상 의존하지 않음  
→ 클린 아키텍처 관점에서 큰 장점

---

## 6. 스프링 예외를 왜 RuntimeException으로 설계?
1. throws 체인 오염 방지
2. 트랜잭션 AOP와 rollback 정책을 자연스럽게 맞추기 위함
3. 실무에서는 대부분 예외 복구가 불가능하기 때문

---

## 7. DataAccessException의 장점 정리
- 공통 예외 계층 (일관성)
- DB 독립성
- 트랜잭션 rollback 정책 통합
- checked 예외 제거로 코드 간결화
- 반복되는 예외 처리 로직 제거

---

## 8. 핵심 요약
- 스프링 JDBC는 예외 처리의 모든 반복을 제거하기 위해 존재한다.
- SQLException은 스프링이 자동으로 DataAccessException으로 변환한다.
- 비즈니스 로직에서는 예외 코드를 거의 작성하지 않게 된다.
- "예외 처리"는 기술이 맡고, "로직"은 개발자가 집중하는 구조가 된다.