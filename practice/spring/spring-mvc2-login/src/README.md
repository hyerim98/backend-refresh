# Section 07 - 로그인 처리 흐름

> 로그인 상태 유지 구조를 단계별로 이해하는 것이 핵심이다.

---

# ✅ v1 - 쿠키 기반 로그인

1. 사용자 로그인 요청
2. 로그인 성공
3. 사용자 ID를 쿠키에 저장
4. 이후 요청마다 쿠키 전송
5. 서버가 쿠키 값으로 사용자 조회

## 문제점

- 쿠키 변조 가능
- 보안 취약
- 신뢰할 수 없음

---

# ✅ v2 - 세션 기반 로그인

1. 사용자 로그인 요청
2. 로그인 성공
3. 서버가 세션 생성
4. 세션 저장소에 사용자 정보 저장
5. 세션 ID를 쿠키로 전달
6. 이후 요청 시 세션 ID 전달
7. 서버가 세션 저장소에서 사용자 조회

---

# ✅ 세션 동작 상세 흐름

요청  
↓  
JSESSIONID 쿠키 확인  
↓  
세션 ID 존재하면 기존 세션 조회  
↓  
없으면 새 세션 생성

---

# ✅ 로그인 성공 시 처리

세션 생성  
↓  
세션에 사용자 객체 저장  
↓  
리다이렉트

---

# ✅ 로그인 실패 시 처리

세션 생성하지 않음  
↓  
다시 로그인 화면 반환

---

# ✅ 로그아웃 처리

세션 무효화  
↓  
invalidate() 호출  
↓  
세션 데이터 제거

---

# 🔥 최종 로그인 구조

HTTP 요청  
↓  
쿠키에 세션 ID 포함  
↓  
서버 세션 저장소 조회  
↓  
사용자 인증 상태 유지

---

# 🔥 한 줄 정리

쿠키는 세션 ID를 전달하는 수단이고  
실제 로그인 정보는 서버 세션에 저장된다.


# Section 08 - 필터 & 인터셉터 동작 흐름

> 호출 순서와 체인 구조를 이해하는 것이 핵심

---

# ✅ 1️⃣ 필터 호출 전체 흐름

요청 발생  
↓  
WAS  
↓  
Filter1.doFilter()
↓
Filter2.doFilter()
↓
DispatcherServlet
↓
Controller
↑
Filter2 응답 처리
↑
Filter1 응답 처리
↓
응답 반환

---

# 🔥 필터 체인 특징

- doFilter() 안에서 chain.doFilter() 호출해야 다음 단계 실행
- chain 호출 전 → 요청 전 처리
- chain 호출 후 → 응답 후 처리

---

# ✅ 2️⃣ 인터셉터 호출 흐름

요청 발생  
↓  
DispatcherServlet  
↓  
Interceptor1.preHandle()
↓
Interceptor2.preHandle()
↓
Controller 실행
↓
Interceptor2.postHandle()
↑
Interceptor1.postHandle()
↓
View 렌더링
↓
Interceptor2.afterCompletion()
↑
Interceptor1.afterCompletion()

---

# 🔥 인터셉터 체인 특징

preHandle → 정방향 실행  
postHandle → 역방향 실행  
afterCompletion → 역방향 실행

---

# ✅ 로그인 체크 흐름 (인터셉터 기준)

요청  
↓  
preHandle에서 세션 확인  
↓  
세션 없으면 false 반환  
↓  
컨트롤러 실행 안됨  
↓  
redirect 로그인 페이지

---

# ✅ 예외 발생 시 흐름

Controller에서 예외 발생  
↓  
postHandle은 실행되지 않음  
↓  
afterCompletion은 항상 실행

---

# 🔥 필터 vs 인터셉터 전체 비교 흐름

요청  
↓  
Filter  
↓  
DispatcherServlet  
↓  
Interceptor  
↓  
Controller  
↓  
View  
↓  
Interceptor.afterCompletion  
↓  
Filter 응답 처리

---

# 🔥 한 줄 핵심 정리

필터는 서블릿 레벨 체인  
인터셉터는 스프링 MVC 체인이다.

로그인 체크는 인터셉터의 preHandle에서 처리한다.



# Section 08 - ArgumentResolver 적용 흐름

> 로그인 세션 처리 개선을 중심으로 정리

---

# ✅ v1 - 기존 세션 직접 조회 방식

Controller 내부에서:

1. HttpSession 조회
2. 세션 존재 여부 확인
3. loginMember 꺼내기
4. null 체크

문제점:

- 중복 코드 발생
- 컨트롤러 책임 증가
- 가독성 저하

---

# ✅ v2 - 인터셉터로 로그인 체크 분리

preHandle에서:

- 세션 확인
- 로그인 여부 판단
- 미로그인 시 redirect

하지만:

- 컨트롤러에서 여전히 세션 꺼내야 함

---

# ✅ v3 - ArgumentResolver 적용

@Login 애노테이션 생성  
↓  
HandlerMethodArgumentResolver 구현  
↓  
supportsParameter()
- @Login 존재 여부 확인  
  ↓  
  resolveArgument()
- 세션 조회
- loginMember 반환

---

# 🔥 전체 호출 흐름

요청  
↓  
Filter  
↓  
Interceptor.preHandle  
↓  
ArgumentResolver.resolveArgument  
↓  
Controller 실행  
↓  
Interceptor.postHandle  
↓  
Interceptor.afterCompletion

---

# 🔥 핵심 변화

세션 조회 코드가

Controller → Resolver 내부로 이동

Controller는

비즈니스 로직만 담당하게 된다.

---

# 🔥 한 줄 정리

ArgumentResolver는

반복되는 파라미터 생성 로직을  
Spring MVC 내부로 위임하는 구조 개선 기술이다.