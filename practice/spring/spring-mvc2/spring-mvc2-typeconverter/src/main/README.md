# Section 11 - 타입 컨버터 발전 과정

Spring MVC에서
타입 변환이 어떻게 발전하는지 정리

---

# v1 기본 타입 변환

HTTP 요청은 모두 문자열로 전달된다.

예

/members?id=10

요청 데이터

"id" = "10"

문제

Controller에서 숫자 타입 사용 시
직접 변환해야 한다.

예

Long id = Long.valueOf(param);

문제점

- 코드 중복
- 변환 로직 증가

---

# v2 Converter 도입

Spring Converter 사용

역할

특정 타입 변환 담당

예

- String → Integer
- String → Boolean
- String → LocalDate

장점

타입 변환 로직을
재사용할 수 있다.

---

# v3 ConversionService 사용

Converter를 관리하는 서비스(컨버터들을 등록하여 사용)

구조

Converter  
↓  
ConversionService  
↓  
타입 변환 실행

장점

- Converter 중앙 관리
- 다양한 타입 변환 지원

---

# v4 Spring MVC 자동 적용

Spring MVC는 내부적으로
ConversionService를 사용한다.

자동 변환 위치

- @RequestParam
- @ModelAttribute
- @PathVariable
- View 렌더링

즉

개발자가 직접 변환하지 않아도
Spring이 자동으로 타입 변환을 수행한다.

---

# v5 Formatter 도입

Formatter는

문자열 ↔ 객체 변환을 담당한다.

특징

- Locale 지원
- UI 출력에 적합

예

1000 → 1,000

---

# v6 Spring 기본 Formatter 사용

Spring이 제공하는 기본 Formatter

- @NumberFormat
- @DateTimeFormat

사용 예

가격

@NumberFormat(pattern = "###,###")

날짜

@DateTimeFormat(pattern = "yyyy-MM-dd")

장점

- 별도 Formatter 구현 필요 없음
- 간단한 포맷 지정 가능

---

# 최종 타입 변환 구조

요청  
↓  
DispatcherServlet  
↓  
ConversionService  
↓  
Converter / Formatter  
↓  
Controller 파라미터 전달

---

# 핵심 정리

Spring MVC는

ConversionService를 중심으로

- Converter
- Formatter

를 사용하여

문자열 데이터를
자동으로 객체 타입으로 변환한다.