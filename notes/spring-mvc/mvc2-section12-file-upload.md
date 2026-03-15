# Section 12 - 파일 업로드

웹 애플리케이션에서 파일 업로드는
multipart/form-data 형식을 사용한다.

HTML Form에서 파일을 전송하면
HTTP 요청이 multipart 형식으로 전달된다.

---

# 1️⃣ multipart/form-data

파일 업로드 요청은 일반 form 요청과 다르게

multipart/form-data

형식을 사용한다.

특징

- 여러 데이터를 하나의 요청으로 전송
- 파일 데이터 포함 가능
- 텍스트 + 파일 함께 전송 가능

예

form 데이터  
파일 데이터

---

# 2️⃣ multipart 요청 구조

multipart 요청은 여러 파트로 구성된다.

각 파트는

- 헤더
- 바디

구조를 가진다.

예

일반 텍스트 데이터  
파일 데이터

각각 독립적인 part로 전송된다.

---

# 3️⃣ Servlet 파일 업로드 처리

서블릿에서는

Part

객체를 사용해 파일을 처리한다.

특징

- multipart 데이터 접근
- 파일 이름 확인
- 파일 저장 가능

하지만

코드가 복잡하고
사용이 불편하다.

---

# 4️⃣ Spring MultipartFile

Spring은 파일 업로드를 쉽게 처리하기 위해

MultipartFile

인터페이스를 제공한다.

특징

- 업로드 파일 접근
- 파일 이름 조회
- 파일 저장 기능 제공

주요 기능

파일 이름 조회  
파일 크기 조회  
파일 저장

---

# 5️⃣ MultipartResolver

Spring MVC는 multipart 요청 처리를 위해

MultipartResolver

를 사용한다.

역할

multipart 요청을 처리하고

MultipartFile

객체로 변환한다.

요청 흐름

요청  
↓  
DispatcherServlet  
↓  
MultipartResolver  
↓  
MultipartHttpServletRequest 생성  
↓  
Controller

---

# 6️⃣ Spring Boot multipart 지원

Spring Boot는

StandardServletMultipartResolver

를 기본으로 사용한다.

따라서

추가 설정 없이도
파일 업로드 기능을 사용할 수 있다.

---

# 7️⃣ 파일 업로드 처리 흐름

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
Controller 처리

---

# 8️⃣ 파일 저장

업로드 파일은 서버에 저장해야 한다.

저장 방식

- 로컬 파일 시스템
- 클라우드 스토리지
- 데이터베이스

일반적으로

파일 → 서버 디스크 저장  
파일 정보 → DB 저장

구조를 사용한다.

---

# 9️⃣ 파일 업로드 주의사항

파일 업로드 시 다음 사항을 고려해야 한다.

- 파일 저장 경로 관리
- 파일 이름 중복 방지
- 보안 검사
- 파일 크기 제한

특히

업로드 파일 이름은
UUID 등을 사용하여
중복을 방지하는 것이 좋다.

---

# 🔥 핵심 정리

Spring MVC는

MultipartResolver를 사용하여

multipart 요청을

MultipartFile 객체로 변환하여
파일 업로드를 쉽게 처리할 수 있도록 한다.