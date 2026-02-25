# Section 05 - Validation

> Validation은 사용자 입력 값이 올바른지 검증하는 과정이다.  
> 핵심은 "검증 실패 시 자연스럽게 다시 화면으로 돌려보내는 구조"를 이해하는 것이다.

---

# 1️⃣ 검증 방식의 발전

## 1. 단순 if 검증

- 컨트롤러 내부에서 직접 조건 검사
- 오류 메시지 Model에 직접 추가
- 재사용 불가
- 구조가 깔끔하지 않음

---

## 2. BindingResult 사용

- 스프링이 제공하는 검증 오류 저장 객체
- 바인딩 오류 + 검증 오류 모두 저장
- View로 오류 정보 전달 가능

📌 Validation의 핵심 객체

---

# 2️⃣ BindingResult의 역할

✔ 필드 오류 저장  
✔ 글로벌 오류 저장  
✔ 바인딩 실패 정보 저장  
✔ 입력값 유지 지원

---

# 3️⃣ FieldError vs ObjectError

## FieldError

- 특정 필드에 대한 오류
- 예: 가격 범위 오류

## ObjectError

- 객체 전체에 대한 오류
- 예: 가격 × 수량 총합 오류

---

# 4️⃣ rejectValue vs reject

## rejectValue

- 특정 필드 오류 추가
- FieldError 생성

## reject

- 객체 전체 오류 추가
- ObjectError 생성

---

# 5️⃣ 오류 코드 체계

예:

required.item.name  
range.item.price

스프링은 오류 발생 시 여러 단계의 코드 후보를 생성한다.

---

# 6️⃣ MessageCodesResolver 동작

rejectValue("price", "range") 호출 시

생성되는 코드 후보 예:

1. range.item.price
2. range.price
3. range.java.lang.Integer
4. range

→ 위에서부터 메시지 파일에서 탐색

---

# 7️⃣ BindingResult 위치 규칙

@ModelAttribute 바로 뒤에 위치해야 한다.

순서가 틀리면 예외 발생.

---

# 8️⃣ 검증 실패 시 처리 구조

- BindingResult에 오류 존재
- return form 화면
- 입력값 유지
- 오류 메시지 출력

---

# 🔥 섹션5 핵심 정리

Validation의 본질은

✔ 검증 로직 수행  
✔ 오류 저장  
✔ 다시 View로 전달  
✔ 입력값 유지  
✔ 오류 메시지 출력

을 자연스럽게 처리하는 구조를 이해하는 것이다.