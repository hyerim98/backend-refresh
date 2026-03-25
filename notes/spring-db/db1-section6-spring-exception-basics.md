# 섹션6. 자바 예외 이해

## 1. 예외 계층 구조
- 최상위: Throwable
    - Error: 시스템 레벨 오류, 복구 불가
    - Exception
        - Checked Exception
        - Unchecked Exception(RuntimeException)

## 2. Checked Exception(Exception)
### 특징
- 컴파일러가 처리 강제
- throws 또는 try/catch 필요
### 장단점
- 장점: IDE가 예외 흐름을 명확히 보여줌
- 단점: 계층 깊어질수록 throws 누적 → 코드 오염

## 3. Unchecked Exception(RuntimeException)
### 특징
- 처리 강제 없음(throws 또는 try/catch 필요 없음)
- 트랜잭션 rollback 기본 정책과 궁합 좋음
### 장단점
- 장점: 코드 간결, 예외 전파 자유롭다
- 단점: API가 어떤 예외가 발생하는지 명시성이 떨어짐

## 4. Checked vs Unchecked 선택 기준
- Checked: 호출자가 반드시 복구해야 하는 경우 (파일, 네트워크, 외부 리소스)
- Unchecked: 개발자가 복구 불가한 예외, 로직 오류, 도메인 예외(**보통 언체크 예외 사용**)

## 5. 예외 래핑(Exception Wrapping)
외부 예외를 의미 있는 내부 예외로 바꿔 던지는 것  
이때 **cause(원인)** 반드시 포함해야 함

예: SQLException → MyDbException 변환

```
try {
      run();
    }
    catch (SQLException e) {
      throw new RuntimeSQLException("DB오류", e); // 중요!
    }
```

## 6. 예외 로깅 전략
- 예외를 catch 하는 곳에서만 로그 남기기
- 여러 레이어에서 중복 로깅 금지
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

## 7. 핵심 정리
- Checked는 외부 API 사용 시에만 제한적으로 사용
- 대부분 비즈니스, 로직 오류는 RuntimeException
- 예외 전환 시 cause 포함 필수
- 로그는 한 곳에서만 남긴다