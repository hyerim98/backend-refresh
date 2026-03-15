# Section 04 - 국제화 동작 흐름

> 국제화는 요청에 따라 다른 메시지를 선택하는 구조를 이해하는 것이 핵심이다.

---

# 1️⃣ 전체 동작 흐름

클라이언트 요청  
↓  
Accept-Language 헤더 포함  
↓  
Spring의 LocaleResolver 동작  
↓  
Locale 결정  
↓  
MessageSource가 해당 언어 파일 선택  
↓  
key에 맞는 메시지 반환

---

# 2️⃣ 타임리프 메시지 렌더링 흐름

Controller  
↓  
View 반환  
↓  
타임리프가 #{key} 발견  
↓  
MessageSource 호출  
↓  
Locale에 맞는 메시지 조회  
↓  
HTML에 문자열 렌더링

---

# 3️⃣ 파라미터 메시지 처리 흐름

#{key(param)}

↓

MessageSource가  
{0}, {1} 위치에 값 치환

↓

완성된 문자열 반환

---

# 4️⃣ Locale 변경 방식

## 1️⃣ 기본 방식

브라우저의 Accept-Language 헤더 기반

---

## 2️⃣ 세션 기반

- 사용자가 언어 변경
- 세션에 Locale 저장
- 이후 요청부터 해당 언어 사용

---

# 5️⃣ 메시지 파일 선택 우선순위

예:

messages_ko.properties  
messages.properties

ko 요청 시  
→ messages_ko 우선  
→ 없으면 기본 messages 사용

---

# 6️⃣ 국제화의 설계 목적

✔ 하드코딩 제거  
✔ 다국어 지원  
✔ 유지보수성 향상  
✔ 확장성 확보

---

# 🔥 한 줄 정리

국제화는

요청의 Locale을 기준으로  
적절한 메시지 파일을 선택하여  
key 값을 실제 문자열로 변환하는 구조이다.