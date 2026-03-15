# 🌐 Spring MVC2 - 백엔드 웹 개발 활용 기술

김영한님의  
**Spring MVC 2편 - 백엔드 웹 개발 활용 기술** 강의를 학습하며  
Spring MVC 기반 웹 애플리케이션에서 사용하는 다양한 실무 기능을 정리했습니다.

---

# 📚 Study Structure
- **notes** : 개념 및 동작 원리 정리
- **practice** : 강의 코드 기반 구현 흐름 정리

---

# 📘 notes (개념 정리)

Spring MVC 기반 웹 애플리케이션에서 자주 사용하는  
실무 기능과 내부 동작 구조를 정리했습니다.

---

## 1. Thymeleaf 기본 기능

Spring MVC에서 사용하는 서버 사이드 템플릿 엔진

주요 기능

- 텍스트 출력
- 변수 표현식
- 조건문
- 반복문
- URL 링크
- 자바스크립트 인라인
- 템플릿 조각(fragment)
- 템플릿 레이아웃

---

## 2. Thymeleaf Form 처리

HTML Form 데이터를 객체로 바인딩하는 기능

주요 기능

- th:object
- th:field
- 입력 폼 바인딩
- 체크박스 처리
- 라디오 버튼 처리
- 셀렉트 박스 처리

---

## 3. 메시지, 국제화 (i18n)

다국어 메시지 관리 기능

주요 개념

- MessageSource
- 메시지 파일 관리
- Locale
- 국제화 처리

---

## 4. Validation

사용자 입력 데이터 검증

주요 기능

- BindingResult
- FieldError
- ObjectError
- Validation 처리 흐름

---

## 5. Bean Validation

Java 표준 검증 기술

주요 기능

- @NotNull
- @NotBlank
- @Range
- @Max
- @Min

Spring Validation과 Bean Validation 통합 구조

---

## 6. 로그인 처리 (쿠키, 세션)

웹 애플리케이션 로그인 처리 방식

주요 개념

- Cookie
- HttpSession
- 세션 기반 로그인
- 로그인 유지 처리

---

## 7. 필터와 인터셉터

요청 처리 과정에서 공통 로직을 처리하는 기술

### Filter

Servlet 기반 요청 필터링

```
Client
 ↓
Filter
 ↓
Servlet
 ↓
Controller
```

### Interceptor

Spring MVC 요청 처리 전후 로직 처리

```
Client
 ↓
Filter
 ↓
DispatcherServlet
 ↓
Interceptor
 ↓
Controller
```

---

## 8. 예외 처리와 오류 페이지

웹 애플리케이션 예외 처리 구조

주요 개념

- 서블릿 오류 처리
- 오류 페이지 처리
- BasicErrorController
- Spring 오류 처리 구조

---

## 9. API 예외 처리

REST API에서 예외 발생 시 처리 방식

주요 기술

- @ExceptionHandler
- @ControllerAdvice
- API 오류 응답 처리

---

## 10. 스프링 타입 컨버터

요청 데이터를 원하는 타입으로 변환하는 기능

주요 개념

- Converter
- ConversionService
- Formatter
- @NumberFormat
- @DateTimeFormat

---

## 11. 파일 업로드

파일 업로드 처리 기능

주요 개념

- multipart/form-data
- MultipartResolver
- MultipartFile
- 파일 업로드 처리 흐름

---

# 📗 practice (구현 정리)

Spring MVC 기능을 실제 웹 애플리케이션에 적용하는 흐름을 정리했습니다.

---

## Thymeleaf 화면 구성

템플릿을 활용한 화면 구성

```
Controller
 ↓
Model 데이터 전달
 ↓
Thymeleaf Template 렌더링
```

---

## Form 데이터 처리

사용자 입력 데이터를 객체에 바인딩

```
Form 요청
 ↓
Data Binding
 ↓
Controller 처리
```

---

## Validation 처리

입력 데이터 검증 흐름

```
요청
 ↓
데이터 바인딩
 ↓
Validation 검사
 ↓
오류 메시지 반환
```

---

## 로그인 처리

세션 기반 로그인 구조

```
로그인 요청
 ↓
사용자 인증
 ↓
Session 생성
 ↓
로그인 상태 유지
```

---

## 인증 체크 구조

로그인이 필요한 요청 처리

```
Client
 ↓
Filter
 ↓
Interceptor
 ↓
Controller
```

---

## 예외 처리 구조

웹 애플리케이션 예외 처리 흐름

```
Controller
 ↓
Exception 발생
 ↓
ExceptionResolver
 ↓
ErrorController
 ↓
Error Page / API Response
```

---

## 타입 변환 처리

요청 데이터를 객체 타입으로 변환

```
Request
 ↓
ConversionService
 ↓
Converter / Formatter
 ↓
Controller 파라미터 전달
```

---

## 파일 업로드 처리

multipart 요청 처리 흐름

```
Client Request
 ↓
DispatcherServlet
 ↓
MultipartResolver
 ↓
MultipartFile
 ↓
Controller
```

---

# 🎯 Study Goal

- Spring MVC 실무 기능 이해
- 웹 애플리케이션 인증 구조 이해
- 예외 처리 및 오류 처리 구조 이해
- 데이터 바인딩 및 Validation 이해
- 파일 업로드 처리 방식 이해