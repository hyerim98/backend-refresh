# Section 04 - 메시지와 국제화

> 국제화(i18n)는 언어별로 다른 메시지를 제공하는 기능이다.

---

# 1️⃣ 메시지 관리의 필요성

문제:

- 화면에 문자열을 직접 작성하면 수정이 어렵다
- 언어 변경이 어렵다
- 유지보수가 힘들다

해결:

- 메시지 파일로 분리
- key 기반 관리

---

# 2️⃣ 메시지 파일 구조

예:

messages.properties  
messages_en.properties  
messages_ko.properties

형식:

key=value

---

# 3️⃣ 메시지 사용 방식

## 3.1 타임리프에서 사용

#{key}

- 현재 Locale에 맞는 메시지 조회

---

## 3.2 자바 코드에서 사용

MessageSource 사용

- key 기반 메시지 조회
- 파라미터 치환 가능

---

# 4️⃣ 파라미터 치환

형식:

{0}, {1}

예:

"상품 이름은 {0} 입니다"

→ 값 치환 가능

---

# 5️⃣ Locale 동작 원리

클라이언트 요청  
↓  
Accept-Language 헤더  
↓  
LocaleResolver  
↓  
적절한 메시지 파일 선택

---

# 6️⃣ LocaleResolver

스프링이 Locale을 결정하는 전략

기본:

AcceptHeaderLocaleResolver

- 요청 헤더 기반

다른 방식:

- 세션 기반
- 쿠키 기반

---

# 7️⃣ 스프링 부트의 자동 설정

- MessageSource 자동 등록
- messages.properties 자동 인식
- 기본 경로: resources/messages

---

# 🔥 섹션4 핵심 정리

국제화는

✔ 메시지를 key 기반으로 관리하고  
✔ Locale에 따라 다른 파일을 선택하며  
✔ 유지보수성과 확장성을 높이는 기능이다.