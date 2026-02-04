# Java static 실습 기록

## 실습 목적
- static 변수와 메서드가 메모리 어디에 저장되는지 확인
- 인스턴스와 static의 생명주기 차이 체감

---

## 1. static 변수 공유 실험

### 코드
- StaticVar.java
- StaticMain.java

### 실험 내용
- 인스턴스 3개 생성
- static 변수 값 증가 확인

### 결과
- 모든 인스턴스에서 같은 값 참조
- Heap이 아니라 Method Area 기준으로 동작함(메서드 영역에서 호출하는 것)

---

## 2. static 메서드 호출 실험

### 코드
- StaticMethod.java
- StaticMain.java

### 실험 내용
- 객체 생성 없이 static 메서드 호출
- static 메서드에서 인스턴스 변수 접근 시도

### 결과
- 컴파일 에러 발생
- static 메서드는 인스턴스 상태에 의존할 수 없음

--- 

## 3. 메모리 관점 정리

| 구분 | 저장 위치 | 생명주기 |
|----|----|----|
| static 변수 | Method Area | JVM 종료까지 |
| 인스턴스 변수 | Heap | 참조 제거 시 |
| 지역 변수 | Stack | 메서드 종료 시 |

---

## 느낀 점
- static은 편리하지만 남용하면 전역 상태가 됨
- 스프링에서 static 사용이 제한적인 이유 이해됨