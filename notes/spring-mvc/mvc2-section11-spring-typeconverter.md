# Section 11 - 스프링 타입 컨버터

웹 애플리케이션에서는 요청 파라미터가 항상 문자열(String) 형태로 전달된다.

하지만 실제 애플리케이션에서는

- 숫자
- Boolean
- LocalDate
- 사용자 정의 객체

등 다양한 타입으로 변환이 필요하다.

Spring MVC는 이러한 문제를 해결하기 위해
타입 컨버터(Type Converter)와 Formatter를 제공한다.

---

# 1️⃣ 타입 변환이 필요한 이유

예

요청

/members?id=10

HTTP 요청 파라미터

"id" = "10"

하지만 Controller에서는

Long id

같은 타입을 사용한다.

즉

문자열 → 숫자

변환이 필요하다.

---

# 2️⃣ Converter

Spring에서 제공하는 타입 변환 인터페이스

역할

한 타입을 다른 타입으로 변환한다.

예

- String → Integer
- String → Boolean
- String → LocalDate
- String → 사용자 객체

개발자가 직접 Converter를 구현하여
사용자 정의 타입 변환도 만들 수 있다.

---

# 3️⃣ ConversionService

Converter를 관리하는 서비스(컨버터들을 등록하여 사용)

역할

- Converter 등록
- 타입 변환 실행

구조

Converter  
↓  
ConversionService  
↓  
타입 변환 수행

즉

여러 Converter를 모아서
실제 변환을 수행하는 역할을 한다.

---

# 4️⃣ DefaultConversionService

Spring이 기본 제공하는 ConversionService

특징

- 기본 타입 Converter 포함
- 사용자 Converter 등록 가능

예

- String → Integer
- String → Boolean
- String → Enum

---

# 5️⃣ Formatter

Converter와 비슷하지만

문자열 ↔ 객체 변환을 담당한다.

특징

- Locale 지원
- 화면 출력(UI)에 적합

예

1000 → 1,000

즉

사용자 화면에 표시되는 데이터 처리에 적합하다.

---

# 6️⃣ Spring 기본 Formatter

Spring은 숫자와 날짜 처리를 위한
기본 Formatter를 제공한다.

대표적인 것

- @NumberFormat
- @DateTimeFormat

이 어노테이션을 사용하면
문자열 ↔ 객체 변환이 자동으로 처리된다.

---

# 7️⃣ @NumberFormat

숫자 포맷을 지정할 때 사용한다.

예

1,000  
10,000

같은 숫자 포맷을 자동으로 변환한다.

사용 목적

- 화면 출력 포맷
- 입력 값 포맷 처리

주요 옵션

pattern

@NumberFormat(pattern = "###,###")

예

1000 → 1,000

style

@NumberFormat(style = Style.NUMBER)

가능한 스타일

NUMBER  
CURRENCY  
PERCENT

예

0.1 → 10%

---

# 8️⃣ @DateTimeFormat

날짜 포맷을 지정할 때 사용한다.

문자열 날짜를
LocalDate, LocalDateTime 등으로 변환한다.

예

2024-01-01  
2024/01/01

주요 옵션

pattern

@DateTimeFormat(pattern = "yyyy-MM-dd")

예

2024-01-01 → LocalDate

iso

@DateTimeFormat(iso = ISO.DATE)

---

# 9️⃣ Formatter 적용 위치

Formatter는 다음 곳에서 자동 적용된다.

- @RequestParam
- @ModelAttribute
- Form 데이터 바인딩
- View 렌더링

즉

요청 데이터와
View 출력 모두에 적용된다.

---

# 🔟 Converter vs Formatter

Converter

- 일반 타입 변환
- 기술 중심

Formatter

- 문자열 ↔ 객체 변환
- UI 중심
- Locale 지원

---

# 🔥 핵심 정리

Spring MVC는

ConversionService를 통해

- Converter
- Formatter

를 관리하며

문자열 요청 데이터를
숫자, 날짜, 객체 타입으로
자동 변환한다.