# Section 08 - ArgumentResolver

> ArgumentResolver는 컨트롤러 파라미터를 자동으로 생성해주는 Spring MVC 내부 전략 객체이다.

---

# 1️⃣ 왜 필요한가?

컨트롤러는 비즈니스 로직만 담당해야 한다.

하지만 실제로는:

- 세션 조회
- 파라미터 변환
- 객체 생성
- null 체크

같은 반복 작업이 발생한다.

이 문제를 해결하기 위해 ArgumentResolver가 사용된다.

---

# 2️⃣ Spring MVC 전체 흐름에서 위치

HTTP 요청  
↓  
Filter  
↓  
DispatcherServlet  
↓  
Interceptor.preHandle  
↓  
HandlerAdapter  
↓  
ArgumentResolver ⭐  
↓  
Controller 실행  
↓  
ReturnValueHandler  
↓  
ViewResolver

---

# 3️⃣ ArgumentResolver의 역할

컨트롤러 호출 직전에:

- 파라미터 타입 확인
- 애노테이션 확인
- 해당 파라미터를 처리할 Resolver 선택
- 객체 생성 후 전달

---

# 4️⃣ 우리가 이미 사용하고 있던 것들

다음도 모두 ArgumentResolver가 처리한다:

- @RequestParam
- @ModelAttribute
- HttpServletRequest
- HttpSession
- @RequestBody

즉, 이미 계속 사용하고 있던 기능이다.

---

# 5️⃣ 로그인 처리와의 연결

기존 방식:

- 세션 직접 조회
- 캐스팅
- null 체크

ArgumentResolver 적용 후:

- @Login Member member

컨트롤러는 로그인 여부 확인 코드 제거 가능

---

# 6️⃣ 동작 원리

1. HandlerAdapter가 컨트롤러 실행 준비
2. 파라미터 목록 확인
3. supportsParameter()로 처리 가능 여부 확인
4. resolveArgument() 실행
5. 생성된 객체를 컨트롤러에 전달

---

# 7️⃣ 인터셉터와의 차이

Interceptor → 요청 흐름 제어  
ArgumentResolver → 파라미터 생성

실행 시점:

Filter  
→ Interceptor.preHandle  
→ ArgumentResolver  
→ Controller

---

# 🔥 핵심 정리

ArgumentResolver는

컨트롤러를 실행하기 전에  
필요한 파라미터 객체를 자동 생성해주는 확장 포인트이다.