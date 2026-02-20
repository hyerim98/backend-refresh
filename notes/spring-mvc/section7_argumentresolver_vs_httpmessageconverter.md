# ArgumentResolver vs HttpMessageConverter

스프링 MVC에서 매우 중요한 두 개념이다.  
헷갈리기 쉬우므로 **역할 기준으로 명확히 구분**해서 이해한다.

---

## 1. 한 줄 정리

| 구분 | 역할 |
|------|------|
| ArgumentResolver | 컨트롤러 **파라미터를 생성/준비** |
| HttpMessageConverter | HTTP Body ↔ 객체 변환 |

---

## 2. ArgumentResolver

### 언제 동작하는가?

컨트롤러 메서드 실행 **직전**

핸들러 어댑터가 컨트롤러를 호출하기 전에  
"이 파라미터를 어떻게 만들어줄까?" 를 해결한다.

---

### 해결하는 문제

컨트롤러 메서드에 다음과 같은 파라미터가 있을 때:

- HttpServletRequest
- HttpServletResponse
- Model
- @RequestParam
- @ModelAttribute
- @PathVariable
- @RequestBody

스프링이 대신:

- 객체 생성
- 요청 값 바인딩
- 타입 변환

을 수행한다.

---

### 핵심 개념

> 컨트롤러 메서드의 파라미터를 만들어주는 전략 객체

---

## 3. HttpMessageConverter

### 언제 동작하는가?

다음 어노테이션이 있을 때 동작한다:

- @RequestBody
- @ResponseBody
- @RestController

즉, **HTTP 메시지 Body를 직접 다룰 때만 동작한다.**

---

### 해결하는 문제

#### 요청 처리 시
HTTP Body (JSON 등) → 자바 객체

#### 응답 처리 시
자바 객체 → HTTP Body (JSON 등)

---

### 핵심 개념

> HTTP Body의 데이터를 객체로 변환하거나  
> 객체를 HTTP Body에 써주는 역할

---

## 4. 전체 동작 흐름

요청 도착  
↓  
HandlerMapping  
↓  
HandlerAdapter  
↓  
ArgumentResolver (파라미터 준비)  
&nbsp;&nbsp;&nbsp;&nbsp;↓  
&nbsp;&nbsp;&nbsp;&nbsp;만약 @RequestBody가 있다면  
&nbsp;&nbsp;&nbsp;&nbsp;→ 내부에서 HttpMessageConverter 사용  
↓  
컨트롤러 실행  
↓  
@ResponseBody가 있다면  
→ HttpMessageConverter 사용

---

## 5. 결정적인 차이

| 기준 | ArgumentResolver | HttpMessageConverter |
|------|------------------|---------------------|
| 관심사 | 컨트롤러 파라미터 준비 | HTTP Body 데이터 변환 |
| 동작 시점 | 컨트롤러 호출 직전 | Body 읽기/쓰기 시 |
| JSON 관련 | 간접적 | 직접적 |
| View 렌더링과 관련 | O | X |

---

## 6. 가장 중요한 이해 포인트

`@RequestBody`가 있을 경우:

1. ArgumentResolver가 해당 파라미터를 감지한다.
2. 내부에서 HttpMessageConverter를 호출한다.
3. HTTP Body → 객체 변환 후 컨트롤러에 전달한다.

즉,

> HttpMessageConverter는 ArgumentResolver 안에서 사용되는 도구이다.

---

## 7. 비유로 이해하기

- ArgumentResolver → 요리사에게 재료를 손질해서 전달하는 사람
- HttpMessageConverter → 재료를 포장지에서 꺼내거나 다시 포장하는 사람

---

이 내용을 이해하면  
스프링 MVC 내부 동작 구조를 상당 부분 이해한 것이다.




# Spring MVC 전체 요청 처리 흐름 정리

ArgumentResolver와 HttpMessageConverter가  
어디에서 어떻게 동작하는지 포함한 전체 구조 정리

---

# 1️⃣ 전체 요청 흐름

클라이언트 요청  
↓  
DispatcherServlet  
↓  
HandlerMapping  
↓  
HandlerAdapter  
↓  
ArgumentResolver (컨트롤러 파라미터 준비)  
&nbsp;&nbsp;&nbsp;&nbsp;↓  
&nbsp;&nbsp;&nbsp;&nbsp;@RequestBody인 경우  
&nbsp;&nbsp;&nbsp;&nbsp;→ HttpMessageConverter 사용  
↓  
컨트롤러 실행  
↓  
반환값 처리  
&nbsp;&nbsp;&nbsp;&nbsp;↓  
&nbsp;&nbsp;&nbsp;&nbsp;@ResponseBody인 경우  
&nbsp;&nbsp;&nbsp;&nbsp;→ HttpMessageConverter 사용  
↓  
ViewResolver (View 반환 시)  
↓  
응답 반환

---

# 2️⃣ 각 구성요소 역할

## 2.1 DispatcherServlet

Spring MVC의 프론트 컨트롤러  
모든 요청을 가장 먼저 받는다.

---

## 2.2 HandlerMapping

요청 URL을 기반으로  
어떤 컨트롤러가 실행될지 찾는다.

---

## 2.3 HandlerAdapter

찾은 컨트롤러를 실제로 실행하는 역할  
이 안에서 ArgumentResolver가 동작한다.

---

# 3️⃣ ArgumentResolver

## 역할

> 컨트롤러 메서드의 파라미터를 생성/바인딩한다.

예:

- HttpServletRequest
- @RequestParam
- @PathVariable
- @ModelAttribute
- @RequestBody

컨트롤러 실행 직전에 동작한다.

---

## 핵심 포인트

ArgumentResolver는

- 파라미터 생성 전략
- 타입 변환
- 데이터 바인딩

을 담당한다.

---

# 4️⃣ HttpMessageConverter

## 역할

> HTTP Body ↔ 객체 변환 담당

---

## 요청 처리 시

HTTP Body(JSON 등)  
→ 자바 객체 변환

---

## 응답 처리 시

자바 객체  
→ HTTP Body(JSON 등) 변환

---

# 5️⃣ @RequestBody 동작 내부 흐름

컨트롤러:

```java
@PostMapping("/users")
public void save(@RequestBody User user) {}
```

동작 순서:

1. HandlerAdapter가 컨트롤러 호출 준비
2. ArgumentResolver가 `@RequestBody` 파라미터 감지
3. 내부에서 HttpMessageConverter 호출
4. JSON → User 객체 변환
5. 변환된 객체를 컨트롤러에 전달

---

# 6️⃣ @ResponseBody 동작 내부 흐름

컨트롤러:

```java
@GetMapping("/users")
@ResponseBody
public User getUser() {
    return new User("kim");
}
```

동작 순서:

1. 컨트롤러 실행
2. 반환값을 HandlerAdapter가 받음
3. HttpMessageConverter 호출
4. User 객체 → JSON 변환
5. HTTP 응답으로 전달

---

# 7️⃣ 두 개념의 관계

| 구분 | ArgumentResolver | HttpMessageConverter |
|------|------------------|---------------------|
| 책임 | 파라미터 생성 | Body 데이터 변환 |
| 동작 위치 | 컨트롤러 실행 전 | Body 읽기/쓰기 시 |
| JSON 직접 처리 | X | O |
| 관계 | 내부에서 Converter 사용 | 도구 역할 |

---

# 8️⃣ 핵심 정리

✔ ArgumentResolver = 파라미터를 만들어주는 전략 객체  
✔ HttpMessageConverter = HTTP Body를 변환하는 객체  
✔ @RequestBody가 있을 때 Resolver 내부에서 Converter 사용  
✔ @ResponseBody는 반환 시 Converter 사용

---

# 9️⃣ 완전 이해 포인트

Spring MVC 구조는

"요청 매핑"  
→ "파라미터 준비"  
→ "컨트롤러 실행"  
→ "반환값 처리"

이 4단계 구조로 이해하면 가장 명확하다.