# Section 09 - 서블릿 예외 처리와 오류 페이지

웹 애플리케이션에서는 실행 중 다양한 예외가 발생할 수 있다.

예를 들어

- 서버 내부 오류
- 존재하지 않는 페이지
- 비즈니스 로직 예외

이러한 예외를 처리하지 않으면
사용자에게 WAS 기본 오류 페이지가 노출된다.

따라서 웹 애플리케이션에서는
오류 페이지를 별도로 처리해야 한다.

---

# 1️⃣ 기본 예외 처리 흐름

컨트롤러에서 예외가 발생하면 다음 흐름이 발생한다.

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

별도의 설정이 없으면
WAS가 기본 오류 화면을 보여준다.

---

# 2️⃣ 서블릿 오류 페이지 등록

서블릿에서는 다음 두 가지 기준으로 오류 페이지를 등록할 수 있다.

1️⃣ Exception 기준  
2️⃣ HTTP 상태 코드 기준

예

- 404 Not Found
- 500 Internal Server Error
- 특정 Exception

Spring Boot에서는  
WebServerCustomizer를 사용해 오류 페이지를 등록할 수 있다.

---

# 3️⃣ 오류 페이지 처리 흐름

Controller에서 예외가 발생하면

WAS가 내부적으로 오류 페이지 요청을 다시 생성한다.

요청 흐름

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
WAS 오류 처리  
↓  
ErrorPage 매핑  
↓  
오류 페이지 요청 발생  
↓  
Filter (다시 실행)  
↓  
DispatcherServlet (다시 실행)  
↓  
Interceptor (다시 실행)  
↓  
Error Controller  
↓  
Error View 반환

즉

오류 페이지 요청이 다시 발생하면서  
필터, 서블릿, 인터셉터가 다시 실행된다.

---

# 4️⃣ 오류 요청 특징

오류 페이지 요청은
일반 요청과 다른 DispatcherType을 가진다.

대표적인 DispatcherType

REQUEST → 일반 요청  
ERROR → 오류 요청  
FORWARD → 서버 내부 이동  
INCLUDE → 다른 서블릿 포함  
ASYNC → 비동기 요청

일반 요청은

DispatcherType.REQUEST

오류 페이지 요청은

DispatcherType.ERROR

이다.

---

# 5️⃣ 오류 정보 전달

오류 페이지 Controller에서는
다음 정보를 확인할 수 있다.

대표 정보

- 오류 상태 코드
- 예외 객체
- 요청 URI
- 오류 메시지

이 정보들은 request attribute로 전달된다.

---

# 6️⃣ 오류 처리 시 발생하는 문제

오류 페이지 요청이 새로 발생하면서

Filter  
Interceptor

가 다시 실행된다.

예를 들어 로그인 인터셉터가 있다면

정상 요청

요청  
→ 로그인 인터셉터  
→ Controller

오류 요청

요청  
→ 로그인 인터셉터  
→ Controller  
→ Exception 발생  
→ 오류 페이지 요청  
→ 로그인 인터셉터 다시 실행

이로 인해

- 불필요한 로직 실행
- 성능 비효율
- 무한 redirect 가능성

문제가 발생할 수 있다.

---

# 7️⃣ 해결 방법

## 필터 해결 방법

필터 등록 시 DispatcherType을 제한한다.

REQUEST 타입에서만 필터 실행

즉

일반 요청 → 필터 실행  
오류 요청 → 필터 실행 안함

---

## 인터셉터 해결 방법

인터셉터 설정 시

오류 페이지 경로를 제외한다.

예

/error-page/** 제외

그러면

정상 요청 → 인터셉터 실행  
오류 요청 → 인터셉터 실행 안함

---

# 🔥 핵심 정리

예외가 발생하면

WAS가 ERROR 타입의 새로운 요청을 생성하고  
그 요청이 다시 Spring MVC로 전달된다.

이 과정에서

Filter  
DispatcherServlet  
Interceptor

가 다시 실행될 수 있으므로

DispatcherType 설정과  
경로 제외 설정으로 이를 제어해야 한다.