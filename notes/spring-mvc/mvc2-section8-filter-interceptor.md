# Section 08 - 로그인 처리2 (필터, 인터셉터)

> 로그인 체크 로직을 컨트롤러에서 분리하기 위한 기술

---

# 1️⃣ 왜 필요한가?

기존 방식:

- 컨트롤러마다 로그인 체크 코드 작성
- 중복 발생
- 유지보수 어려움

해결:

✔ 공통 로직을 분리  
✔ 요청 공통 처리 영역 활용

---

# 2️⃣ 필터 (Filter)

## 위치

WAS ↔ Servlet 사이

Spring MVC보다 앞에서 동작

---

## 호출 흐름

HTTP 요청  
↓  
WAS  
↓  
Filter1  
↓  
Filter2  
↓  
DispatcherServlet  
↓  
Controller

---

## 필터 체인 구조

여러 필터는 체인 형태로 연결됨.

각 필터는 반드시:

chain.doFilter(request, response)

를 호출해야 다음 단계로 넘어간다.

호출하지 않으면 요청이 중단된다.

---

## 필터 주요 메서드

### init()

- 서버 시작 시 1번 호출
- 필터 초기화

---

### doFilter()

- 요청마다 호출
- 필터 핵심 로직 작성 위치

동작 구조:

1. 요청 전 처리
2. chain.doFilter()
3. 응답 후 처리

---

### destroy()

- 서버 종료 시 1번 호출
- 자원 정리

---

# 3️⃣ 인터셉터 (Interceptor)

## 위치

DispatcherServlet ↔ Controller 사이

Spring MVC 내부에서 동작

---

## 호출 흐름

HTTP 요청  
↓  
WAS  
↓  
Filter  
↓  
DispatcherServlet  
↓  
Interceptor.preHandle()  
↓  
Controller  
↓  
Interceptor.postHandle()  
↓  
View 렌더링  
↓  
Interceptor.afterCompletion()

---

# 4️⃣ 인터셉터 체인 구조

여러 인터셉터도 체인 형태로 동작한다.

preHandle은 순차 호출  
postHandle은 역순 호출  
afterCompletion도 역순 호출

---

# 5️⃣ 인터셉터 주요 메서드

### preHandle()

- 컨트롤러 호출 전에 실행
- 반환값 boolean

true → 다음 단계 진행  
false → 요청 중단

로그인 체크는 여기서 수행

---

### postHandle()

- 컨트롤러 실행 후
- View 렌더링 전

Model 데이터 가공 가능

---

### afterCompletion()

- View 렌더링 완료 후 실행
- 예외 발생 여부 확인 가능
- 자원 정리 목적

---

# 6️⃣ 필터 vs 인터셉터 차이

## 실행 시점

Filter → Spring 이전  
Interceptor → Spring 내부

---

## 대상 범위

Filter → 모든 요청  
Interceptor → 컨트롤러 관련 요청

---

## 기능 목적

Filter → 공통 기술 처리 (로그, 인코딩 등)  
Interceptor → 인증, 인가, 권한 체크

---

# 🔥 핵심 정리

필터는 서블릿 레벨에서 동작하고  
인터셉터는 스프링 MVC 레벨에서 동작한다.

로그인 체크는 인터셉터에서 처리하는 것이 적절하다.