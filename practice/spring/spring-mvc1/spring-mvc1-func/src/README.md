# Section 07 - 스프링 MVC 기본 기능

---

# 1. 요청 매핑의 발전

## 🔴 문제

- URL마다 서블릿을 하나씩 만들어야 했다.
- URL과 메서드를 매핑하기 번거로웠다.

---

## 🟢 해결

스프링 MVC는 하나의 DispatcherServlet이 모든 요청을 받는다.

그리고

- @RequestMapping을 사용해
- URL ↔ 메서드 매핑을 선언적으로 처리한다.

---

## 📌 정리

이제 개발자는
"서블릿 생성"이 아니라
"메서드에 매핑 정보만 선언"하면 된다.

---

# 2. 요청 파라미터 처리의 발전

## 🔴 문제

- HttpServletRequest에서 직접 꺼내야 했다.
- 타입 변환을 직접 해야 했다.
- 객체에 값을 일일이 세팅해야 했다.

---

## 🟢 해결 1 - @RequestParam

요청 파라미터를 변수에 바로 바인딩한다.

→ 단순 값 처리에 적합

---

## 🟢 해결 2 - @ModelAttribute

요청 파라미터를 객체에 자동 바인딩한다.

- 객체 자동 생성
- setter 또는 필드에 자동 주입

→ 폼 처리에서 매우 강력

---

## 📌 핵심

스프링이
"객체 생성 + 값 주입 + 타입 변환"을 대신 해준다.

---

# 3. HTTP 메시지 바디 처리의 발전

## 🔴 문제

- JSON을 직접 읽어야 했다.
- 문자열을 파싱해야 했다.
- 응답 JSON도 직접 만들어야 했다.

---

## 🟢 해결 - HttpMessageConverter

객체 ↔ JSON 자동 변환

- 요청 JSON → 객체
- 객체 → 응답 JSON

---

## 📌 핵심

개발자는
JSON을 직접 다루지 않는다.
객체만 다루면 된다.

---

# 4. 응답 처리의 발전

## 🔴 기존 방식

- PrintWriter로 직접 응답 작성
- JSP 경로 직접 지정

---

## 🟢 해결 1 - ViewResolver

논리 이름만 반환하면
실제 View 경로는 자동 변환

---

## 🟢 해결 2 - @ResponseBody

뷰를 거치지 않고
HTTP 메시지 바디에 직접 응답 작성

→ REST API의 핵심

---

# 5. Argument Resolver의 등장

## 🔴 문제

컨트롤러 파라미터가 많아지면
직접 생성하기 복잡하다.

---

## 🟢 해결

Argument Resolver가

- HttpServletRequest
- HttpServletResponse
- Model
- @RequestParam
- @ModelAttribute

등을 자동으로 생성하고 주입한다.

---

# 6. 전체 동작 흐름 (기능 통합)

1. 요청 발생
2. DispatcherServlet이 요청 수신
3. HandlerMapping이 핸들러 조회
4. HandlerAdapter 실행
5. Argument Resolver가 파라미터 준비
6. 컨트롤러 실행
7. ViewResolver 또는 HttpMessageConverter 동작
8. 응답 반환

---

# 7. 이번 섹션의 진짜 핵심

스프링 MVC는

"반복되는 서블릿 작업을 자동화하고
선언형 방식으로 개발하도록 만든 프레임워크"이다.

---

# 한 줄 결론

스프링 MVC 기본 기능은

"요청 매핑 → 파라미터 바인딩 → 메시지 변환 → 뷰 처리"

의 자동화 시스템이다.