# Section 10 - API 예외 처리

웹 애플리케이션에서는 예외가 발생했을 때
클라이언트에게 적절한 응답을 반환해야 한다.

특히 API 서버에서는 HTML 오류 페이지가 아니라
JSON 형태의 오류 응답을 반환해야 한다.

Spring MVC에서는 다음 방법으로 API 예외 처리를 할 수 있다.

1. HandlerExceptionResolver
2. @ExceptionHandler
3. @ControllerAdvice

---

# 1️⃣ HandlerExceptionResolver

Spring MVC는 예외가 발생하면
HandlerExceptionResolver를 통해 예외를 처리할 수 있다.

역할

Controller에서 발생한 예외를 처리하고  
적절한 응답을 반환한다.

동작 흐름

요청  
↓  
Controller 실행  
↓  
Exception 발생  
↓  
DispatcherServlet  
↓  
HandlerExceptionResolver 실행  
↓  
예외 처리 후 응답 반환

예외를 처리하면

- 오류 페이지 반환
- JSON 응답 반환
- 상태 코드 설정

등을 수행할 수 있다.

---

# 2️⃣ HandlerExceptionResolver 동작 결과

Resolver는 다음 중 하나를 수행할 수 있다.

### 예외 해결

예외를 처리하고 정상 응답 반환

### ModelAndView 반환

오류 페이지 View 반환

### null 반환

예외를 처리하지 못하면
다음 Resolver에게 전달된다.

---

# 3️⃣ Spring 기본 ExceptionResolver

Spring MVC는 기본적으로 다음 Resolver를 제공한다.

1️⃣ ExceptionHandlerExceptionResolver  
→ @ExceptionHandler 처리

2️⃣ ResponseStatusExceptionResolver  
→ @ResponseStatus 처리

3️⃣ DefaultHandlerExceptionResolver  
→ 스프링 내부 예외 처리

---

# 4️⃣ @ExceptionHandler

Controller 내부에서 특정 예외를 처리할 수 있는 어노테이션이다.

특징

- 특정 Exception을 직접 처리 가능
- Controller 내부에서 사용
- API 예외 처리에 매우 많이 사용

예

```
@ExceptionHandler(IllegalArgumentException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public ErrorResult illegalExHandle(IllegalArgumentException e) {
    return new ErrorResult("BAD", e.getMessage());
}
```

예외 발생 시

해당 메서드가 실행된다.

---

# 5️⃣ 여러 예외 처리

하나의 메서드에서 여러 예외를 처리할 수 있다.

```
@ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
```

---

# 6️⃣ ControllerAdvice

여러 Controller에서 발생하는 예외를
한 곳에서 처리할 수 있다.

역할

- 전역 예외 처리
- 공통 API 오류 처리

예

```
@RestControllerAdvice
public class ExControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ErrorResult exceptionHandle(Exception e) {
        return new ErrorResult("EX", "내부 오류");
    }

}
```

---

# 7️⃣ @RestControllerAdvice

다음 두 어노테이션의 조합

```
@ControllerAdvice
@ResponseBody
```

즉

API 예외 처리에서
JSON 응답을 반환할 때 사용한다.

---

# 8️⃣ API 예외 처리 흐름

요청  
↓  
Controller  
↓  
Exception 발생  
↓  
DispatcherServlet  
↓  
ExceptionResolver 실행  
↓  
@ExceptionHandler 실행  
↓  
JSON 오류 응답 반환

---

# 🔥 핵심 정리

API 서버에서는

HTML 오류 페이지 대신  
JSON 형태의 오류 응답을 반환해야 한다.

Spring MVC에서는

- HandlerExceptionResolver
- @ExceptionHandler
- @ControllerAdvice
- @ExceptionHandler + @ControllerAdvice 조합을 많이 사용

를 사용해
예외를 처리한다.