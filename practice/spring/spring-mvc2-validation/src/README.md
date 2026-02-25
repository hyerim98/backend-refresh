# Section 05 - Validation 동작 흐름

> 이 섹션은 코드 문법이 아니라 "전체 흐름 이해"가 핵심이다.

---

# 1️⃣ 전체 흐름

1. 사용자가 폼 제출
2. HTTP 요청 파라미터 생성
3. @ModelAttribute로 객체 바인딩
4. BindingResult 생성
5. 검증 로직 실행
6. 오류 발생 시 BindingResult에 저장
7. 오류 존재하면 form으로 다시 반환
8. th:field로 입력값 유지
9. th:errors로 오류 메시지 출력

---

# 2️⃣ 바인딩 오류 vs 검증 오류

## 바인딩 오류

- 타입 불일치 등
- 스프링이 자동 처리

## 검증 오류

- 비즈니스 조건 위반
- 개발자가 직접 추가

---

# 3️⃣ 필드 오류 흐름

rejectValue 호출  
↓  
FieldError 생성  
↓  
BindingResult 저장  
↓  
View로 전달  
↓  
th:errors가 출력

---

# 4️⃣ 글로벌 오류 흐름

reject 호출  
↓  
ObjectError 생성  
↓  
BindingResult 저장  
↓  
View에서 글로벌 오류 영역 출력

---

# 5️⃣ 입력값 유지 원리

BindingResult는

- 원래 입력값
- 오류 정보

를 함께 보관한다.

타임리프 th:field는

→ BindingResult 값을 우선 사용

그래서 오류 발생 시에도 입력값이 유지된다.

---

# 6️⃣ 메시지 코드 탐색 흐름

rejectValue("price", "range")  
↓  
MessageCodesResolver가 코드 목록 생성  
↓  
messages.properties에서 위에서부터 탐색  
↓  
일치하는 메시지 반환

---

# 7️⃣ Validation 설계 목적

✔ 사용자 경험 향상  
✔ 코드 구조 개선  
✔ 재사용 가능  
✔ 메시지 국제화와 자연스러운 연결

---

# 🔥 한 줄 정리

Validation은

검증 실패 상황에서도  
사용자 입력을 유지하며  
자연스럽게 오류 메시지를 보여주는 구조를 만드는 기술이다.