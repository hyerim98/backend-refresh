# Section 09 - 예외 처리 구조 발전 정리

예외 발생 시 요청 흐름과
오류 페이지 처리 과정을 중심으로 정리

---

# v1 기본 예외 처리

Controller에서 예외 발생

요청 흐름

요청  
↓  
Filter  
↓  
DispatcherServlet  
↓  
Interceptor  
↓  
Controller  
↓  
Exception 발생  
↓  
WAS  
↓  
기본 오류 페이지

문제점

- 사용자 친화적이지 않음
- 오류 화면 제어 불가

---

# v2 오류 페이지 등록

서블릿 오류 페이지 기능 사용

등록 기준

- Exception
- HTTP 상태 코드

예

- 404 오류 페이지
- 500 오류 페이지

이제 예외 발생 시
지정된 오류 페이지로 이동한다.

---

# v3 오류 페이지 Controller 처리

예외 발생 시

WAS가 새로운 오류 요청을 생성한다.

요청 흐름

요청  
↓  
Controller 실행  
↓  
Exception 발생  
↓  
WAS 오류 처리  
↓  
ErrorPage 매핑  
↓  
/error-page/500 요청 발생  
↓  
Error Controller 실행  
↓  
Error View 반환

---

# v4 오류 요청 문제 해결

문제

오류 요청이 다시 발생하면서

Filter  
Interceptor

가 다시 실행된다.

예

로그인 인터셉터 존재

요청  
→ 로그인 체크  
→ Controller  
→ Exception 발생  
→ 오류 페이지 요청  
→ 로그인 체크 다시 실행

---

# v5 해결 방법

필터

DispatcherType.REQUEST만 처리

인터셉터

오류 페이지 경로 제외

예

/error-page/** 제외

---

# 최종 예외 처리 흐름

사용자 요청  
↓  
Filter  
↓  
DispatcherServlet  
↓  
Interceptor  
↓  
Controller  
↓  
Exception 발생  
↓  
WAS  
↓  
ErrorPage 매핑  
↓  
Error Controller  
↓  
Error View 반환

---

# 핵심 정리

예외 발생 시 WAS가 새로운 ERROR 요청을 생성하고  
Spring MVC가 이를 처리하여 사용자에게 오류 페이지를 제공한다.