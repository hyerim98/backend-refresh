# Section 07 - 스프링 MVC 기본 기능

---

# 1. 요청 매핑 (@RequestMapping)

## 기본 개념

클라이언트의 요청 URL을 특정 컨트롤러 메서드와 매핑한다.

---

## 주요 기능

- URL 경로 매핑
- HTTP Method 지정 (GET, POST 등)
- 특정 파라미터 조건 매핑
- 특정 헤더 조건 매핑
- Content-Type 조건 매핑
- Accept 조건 매핑

---

# 2. HTTP 요청 파라미터 조회

## 1) 단순 파라미터 조회

- 쿼리 파라미터
- HTML Form 데이터

요청 파라미터를 다양한 방식으로 조회 가능하다.

---

## 2) @RequestParam

- 요청 파라미터를 변수에 바인딩
- required 옵션으로 필수 여부 지정 가능
- defaultValue 지정 가능

---

## 3) @ModelAttribute

- 요청 파라미터를 객체에 자동 바인딩
- setter 또는 필드에 값 주입
- 객체 생성 후 자동 매핑

→ 폼 처리에서 매우 많이 사용

## 요청 파라미터 VS HTTP 메시지 바디
* 요청 파라미터를 조회하는 기능 : @RequestParam, @ModelAttribute
* HTTP 메시지 바디를 직접 조회하는 기능 : @RequestBody

---

# 3. HTTP 요청 메시지 조회

## 1) 단순 텍스트

- HTTP 메시지 바디를 직접 읽을 수 있다.

---

## 2) JSON 데이터 처리

- JSON → 객체 자동 변환
- HttpMessageConverter가 내부적으로 동작

---

# 4. HttpMessageConverter

## 역할

HTTP 요청/응답 메시지를
객체 ↔ JSON / 문자열 로 변환

---

## 동작 흐름

1. 클라이언트 요청
2. DispatcherServlet
3. Argument Resolver
4. HttpMessageConverter
5. 객체 변환

---

## 대표적인 컨버터

- String 처리 컨버터
- JSON 처리 컨버터

---

# 5. 응답 처리

## 1) 정적 뷰 반환

- ViewResolver가 동작
- 논리 뷰 이름 → 실제 뷰 경로 변환

---

## 2) @ResponseBody

- 뷰를 거치지 않음
- HTTP 메시지 바디에 직접 응답 작성
- JSON 반환 시 필수

---

# 6. Argument Resolver

## 역할

컨트롤러 메서드의 파라미터를 자동으로 생성하고 주입한다.

예:

- HttpServletRequest
- HttpServletResponse
- Model
- @RequestParam
- @ModelAttribute

---

# 7. ViewResolver

## 역할

논리 이름 → 실제 View 객체로 변환

동작 과정:

1. 컨트롤러가 논리 이름 반환
2. ViewResolver가 실제 경로 탐색
3. View 렌더링

---

# 8. 스프링 MVC 기본 흐름 정리

1. 요청 발생
2. DispatcherServlet 호출
3. HandlerMapping이 핸들러 조회
4. HandlerAdapter 실행
5. Argument Resolver로 파라미터 생성
6. 컨트롤러 실행
7. ViewResolver 또는 HttpMessageConverter 동작
8. 응답 반환

---

# 9. 이번 섹션 핵심 정리

1. 요청 매핑의 다양한 조건 이해
2. @RequestParam과 @ModelAttribute 차이
3. HttpMessageConverter의 역할 이해
4. @ResponseBody의 의미 이해
5. ViewResolver 동작 원리 이해

---

# 한 줄 요약

스프링 MVC 기본 기능은
"요청 매핑 + 파라미터 바인딩 + 메시지 변환 + 뷰 처리"
의 전체 흐름을 이해하는 것이다.