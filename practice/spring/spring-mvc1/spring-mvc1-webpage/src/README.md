# Section 08 - 스프링 MVC 웹 페이지 만들기

> 기존에 배운 Spring MVC 구조를  
> 실제 웹 서비스 흐름에 적용하는 단계

---

# 1️⃣ 섹션8의 목적

이 섹션은 새로운 내부 구조를 배우는 단계가 아니라  
Spring MVC의 기능을 실제 웹 서비스 흐름에 적용하는 단계이다.

즉,

"이론 학습"이 아니라  
"전체 연결 실습"에 가깝다.

---

# 2️⃣ 전체 CRUD 흐름 구조

웹 애플리케이션 기본 흐름:

1. 등록 폼 조회
2. 등록 처리
3. 리다이렉트
4. 단건 조회
5. 목록 조회
6. 수정 폼 조회
7. 수정 처리

---

# 3️⃣ 회원 등록 흐름

## 1. 등록 폼 요청 (GET)

- 클라이언트가 등록 페이지 요청
- 컨트롤러가 뷰 이름 반환
- ViewResolver가 뷰 렌더링

📌 핵심
- Model은 비어 있음
- 단순 View 반환

---

## 2. 등록 처리 (POST)

- 사용자가 폼 데이터 전송
- @ModelAttribute로 객체 자동 바인딩
- 저장 로직 수행

📌 핵심
- ArgumentResolver가 객체 생성
- 요청 파라미터 자동 바인딩

---

# 4️⃣ Redirect 처리

## 왜 Redirect를 사용하는가?

POST 요청 후 바로 View를 반환하면

→ 새로고침 시 중복 제출 문제 발생

---

## PRG 패턴 (Post → Redirect → Get)

1. POST 요청
2. 저장 처리
3. Redirect
4. GET 요청으로 결과 조회

📌 핵심
- 중복 제출 방지
- URL 구조 명확화

---

# 5️⃣ 조회 흐름

## 단건 조회

- PathVariable 사용
- Model에 데이터 담기
- View에서 데이터 출력

📌 핵심
- Model은 View에 데이터 전달용 저장소

---

## 목록 조회

- 전체 데이터 조회
- Model에 컬렉션 담기
- 반복 렌더링

---

# 6️⃣ 수정 흐름

## 수정 폼 조회

- 기존 데이터 조회
- Model에 담아서 View 전달
- 입력 폼에 기존 값 출력

---

## 수정 처리

- @ModelAttribute로 값 바인딩
- 기존 데이터 변경
- Redirect 처리

---

# 7️⃣ 섹션8에서 정리해야 할 핵심 개념

✔ @ModelAttribute 실제 활용  
✔ Model의 역할 (View 전달 전용 저장소)  
✔ Redirect와 PRG 패턴  
✔ PathVariable 사용  
✔ GET / POST 역할 구분

---

# 8️⃣ 전체 동작 구조 다시 정리

요청  
↓  
DispatcherServlet  
↓  
HandlerMapping  
↓  
HandlerAdapter  
↓  
ArgumentResolver (파라미터 준비)  
↓  
컨트롤러 실행  
↓  
Redirect 또는 View 반환  
↓  
ViewResolver 또는 HttpMessageConverter  
↓  
응답 반환

---

# 9️⃣ 이번 섹션의 진짜 의미

Spring MVC는

"요청을 받아서 처리하는 기술"이 아니라

"웹 애플리케이션 흐름을 구조적으로 관리하는 프레임워크"라는 것을 이해하는 단계이다.

---

# 🔥 한 줄 정리

섹션8은

Spring MVC의 기능을  
실제 웹 서비스 CRUD 흐름에 연결하는 실전 적용 단계이다.