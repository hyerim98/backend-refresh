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

---

# BasicErrorController

Spring Boot는 기본적으로 오류 처리를 위한 Controller를 제공한다.

바로

BasicErrorController

이다.

개발자가 별도로 ErrorController를 만들지 않아도
Spring Boot가 기본 오류 페이지를 제공하는 이유가 이것 때문이다.

---

# 1️⃣ BasicErrorController 역할

예외가 발생하면

WAS → 오류 요청 생성 → Spring MVC

그리고

BasicErrorController가 이 요청을 처리한다.

요청 흐름

사용자 요청  
↓  
Controller 실행  
↓  
Exception 발생  
↓  
WAS 오류 처리  
↓  
/error 요청 발생  
↓  
BasicErrorController 실행  
↓  
오류 화면 반환

---

# 2️⃣ BasicErrorController 요청 경로

Spring Boot는 기본적으로

```
/error
```

경로를 오류 처리 경로로 사용한다.

예외 발생 시

WAS가

```
/error
```

로 요청을 전달한다.

그리고 이 요청을

BasicErrorController가 처리한다.

---

# 3️⃣ 반환되는 오류 페이지

BasicErrorController는

요청 타입에 따라 다른 결과를 반환한다.

### HTML 요청

브라우저 요청이면

오류 페이지 View 반환

예

```
error/404
error/500
error
```

순서로 View를 찾는다.

예

```
templates/error/404.html
templates/error/500.html
templates/error.html
```

---

### API 요청 (JSON)

API 요청이면

JSON 형태 오류 응답 반환

예

```
{
  "timestamp": "2026-03-08T11:00:00",
  "status": 500,
  "error": "Internal Server Error",
  "path": "/api/members"
}
```

---

# 4️⃣ 오류 정보 제공

BasicErrorController는

ErrorAttributes를 통해
오류 정보를 가져온다.

대표 제공 정보

- timestamp
- status
- error
- exception
- message
- path

이 정보는

request attribute

에서 가져온다.

---

# 5️⃣ 오류 페이지 우선순위

Spring Boot 오류 페이지 탐색 순서

1️⃣ 상태 코드 페이지

```
error/404.html
error/500.html
```

2️⃣ 공통 오류 페이지

```
error.html
```

3️⃣ BasicErrorController 기본 오류 응답

---

# 6️⃣ 핵심 정리

Spring Boot는

BasicErrorController를 기본 제공하여

예외 발생 시

```
/error
```

요청을 처리하고

HTML 요청이면 오류 View를 반환하고  
API 요청이면 JSON 오류 응답을 반환한다.