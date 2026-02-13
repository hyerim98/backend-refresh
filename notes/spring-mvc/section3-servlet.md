# 섹션3. 서블릿 핵심 정리

## 1. 서블릿(Servlet)이란?

- 자바 기반의 웹 요청 처리 기술
- HTTP 요청을 받아 응답을 생성하는 서버 측 프로그램
- WAS(Tomcat 등) 위에서 동작
- 스프링 MVC도 내부적으로 서블릿 기반으로 동작

즉, 서블릿은 스프링 웹 기술의 "기초 바닥"

---

## 2. 서블릿 동작 구조

1. 클라이언트가 HTTP 요청
2. WAS가 요청을 받음
3. HttpServletRequest / HttpServletResponse 객체 생성
4. 해당 URL에 매핑된 서블릿 실행
5. service() → doGet() / doPost() 호출
6. 응답 반환

---

## 3. 서블릿 생명주기

- init() → 최초 1회 실행
- service() → 요청마다 실행
- destroy() → 종료 시 실행

서블릿 객체는 싱글톤으로 관리됨.

---

## 4. HttpServletRequest 역할

클라이언트 요청 정보 조회

### 주요 기능

- HTTP 메서드 조회
- URI 조회
- 헤더 조회
- 파라미터 조회 (GET, form)
- HTTP Body 읽기 (JSON 등)

예:
- request.getParameter("username")
- request.getHeader("host")
- request.getReader()

---

## 5. HttpServletResponse 역할

서버 응답 생성

### 주요 기능

- 상태 코드 지정
- 헤더 설정
- Content-Type 설정
- 응답 바디 작성
- 쿠키 설정
- redirect 설정

예:
- response.setStatus(200)
- response.setContentType("application/json")
- response.getWriter()

---

## 6. HTTP 요청 데이터 전송 방식

### 1) GET
- URL 쿼리 파라미터 사용
- 캐싱 가능

### 2) POST - x-www-form-urlencoded(HTML)
- form 전송
- 내부적으로 쿼리 스트링과 동일하게 처리됨

### 3) POST - API JSON
- HTTP Body에 데이터 포함
- request.getReader()로 직접 읽어야 함

---

## 7. 서블릿의 한계

- 요청/응답 처리 코드가 반복됨
- 비즈니스 로직과 웹 로직이 섞임
- 공통 처리(로그, 인증 등)가 어려움

→ 이런 문제를 해결하기 위해 MVC 패턴 등장

---

## 8. 정리

- 서블릿은 HTTP 요청/응답을 직접 처리하는 저수준 기술
- request / response 객체가 핵심
- 멀티스레드 환경이므로 상태 관리에 주의
- 스프링 MVC는 서블릿 기반 위에서 동작
- MVC는 서블릿의 복잡함을 개선하기 위한 구조