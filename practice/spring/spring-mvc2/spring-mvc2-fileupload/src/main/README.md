# Section 12 - 파일 업로드 발전 과정

Spring MVC에서 파일 업로드 처리 방식의 발전 과정

---

# v1 기본 form 요청

일반 form 요청

application/x-www-form-urlencoded

문제

파일 데이터를 전송할 수 없다.

---

# v2 multipart/form-data 사용

파일 업로드 시

multipart/form-data

형식을 사용한다.

특징

- 텍스트 데이터 전송 가능
- 파일 데이터 전송 가능
- 여러 파트로 구성

---

# v3 Servlet Part 사용

서블릿에서

Part

객체를 사용하여 파일을 처리한다.

기능

- 업로드 파일 접근
- 파일 이름 조회
- 파일 저장

문제

- 코드 복잡
- 사용 불편

---

# v4 Spring MultipartFile 사용

Spring은

MultipartFile

인터페이스를 제공한다.

장점

- 파일 접근 간단
- 파일 저장 편리
- 코드 간결

---

# v5 MultipartResolver 적용

Spring MVC는

MultipartResolver

를 사용하여 multipart 요청을 처리한다.

요청 흐름

요청  
↓  
DispatcherServlet  
↓  
MultipartResolver  
↓  
MultipartHttpServletRequest  
↓  
Controller

---

# v6 Spring Boot 자동 설정

Spring Boot는

StandardServletMultipartResolver

를 자동으로 등록한다.

따라서

별도 설정 없이

MultipartFile

을 바로 사용할 수 있다.

---

# 최종 파일 업로드 구조

요청 (multipart/form-data)  
↓  
DispatcherServlet  
↓  
MultipartResolver  
↓  
MultipartHttpServletRequest  
↓  
MultipartFile  
↓  
Controller

---

# 핵심 정리

Spring MVC는

MultipartResolver를 통해

multipart 요청을 처리하고

MultipartFile 객체를 사용해
파일 업로드를 쉽게 구현할 수 있다.