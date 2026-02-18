# 📘 Section6 - 스프링 MVC 구조 이해

> 목표: 우리가 직접 구현한 MVC 프레임워크(v1~v4)가  
> 스프링 MVC 내부에서 어떻게 동작하는지 구조적으로 이해하기

---

# 1️⃣ 스프링 MVC 전체 흐름

클라이언트 요청  
→ DispatcherServlet  
→ HandlerMapping  
→ HandlerAdapter  
→ Controller 실행  
→ ModelAndView 반환  
→ ViewResolver  
→ View 렌더링

---

# 2️⃣ DispatcherServlet

## 📌 개념
스프링 MVC의 Front Controller

## 📌 역할
- 모든 HTTP 요청을 가장 먼저 받는다
- 공통 로직 처리
- 전체 요청 흐름 제어

## 📌 우리가 만든 구조와 비교
FrontController와 동일한 역할

---

# 3️⃣ HandlerMapping

## 📌 역할
요청 URL에 맞는 핸들러(컨트롤러)를 찾는다

## 📌 특징
- URL 기준 매핑
- HTTP Method 기반 매핑 가능
- @RequestMapping 기반 동작

## 📌 우리가 만든 구조와 비교
handlerMappingMap과 동일한 개념

---

# 4️⃣ HandlerAdapter

## 📌 왜 필요한가?
다양한 형태의 컨트롤러를 실행하기 위해

## 📌 역할
- 해당 핸들러를 지원하는지 판단
- 실제 핸들러 호출
- 실행 결과를 ModelAndView 형태로 변환

## 📌 핵심 의미
확장 가능한 구조를 만드는 핵심 장치

## 📌 우리가 만든 구조와 비교
MyHandlerAdapter와 동일한 개념

---

# 5️⃣ Controller

## 📌 특징
- 비즈니스 로직 수행
- 파라미터 자동 바인딩 지원
- Model 객체 사용 가능
- viewName 반환

## 📌 우리가 만든 v4와 연결
- Model 파라미터 전달 방식
- viewName 반환 구조
- 서블릿 기술에 직접 의존하지 않음

👉 v4 구조와 거의 동일

---

# 6️⃣ ModelAndView

## 📌 역할
- Model 데이터 보관
- 논리적 viewName 보관

## 📌 의미
Controller와 View 사이의 전달 객체

---

# 7️⃣ ViewResolver

## 📌 역할
논리적 viewName을 실제 View 객체로 변환

## 📌 동작 개념
논리 이름 → 실제 경로 변환

예시 개념:
members → 실제 JSP/템플릿 경로

---

# 8️⃣ View

## 📌 역할
- 화면 렌더링
- Model 데이터 기반으로 출력

---

# 9️⃣ 전체 구조 요약

요청  
→ DispatcherServlet  
→ HandlerMapping  
→ HandlerAdapter  
→ Controller  
→ ModelAndView  
→ ViewResolver  
→ View  
→ 렌더링

---

# 🔟 우리가 구현한 MVC vs 스프링 MVC

| 직접 구현 | 스프링 MVC |
|------------|------------|
| FrontController | DispatcherServlet |
| handlerMappingMap | HandlerMapping |
| HandlerAdapter | HandlerAdapter |
| ModelView | ModelAndView |
| MyView | View |
| viewResolver() | ViewResolver |

👉 구조가 거의 동일하다.

---

# 🔥 Section6의 핵심 목적

- 스프링 MVC를 "사용"하는 것이 아니라
- 스프링 MVC를 "이해"하는 것

---

# 💡 핵심 깨달음

1. 스프링 MVC는 단계적으로 발전한 구조다.
2. DispatcherServlet이 전체 흐름을 제어한다.
3. HandlerAdapter가 확장성을 만든다.
4. 우리가 만든 MVC 발전 과정이 그대로 적용되어 있다.

---

# 🧠 한 줄 정리

Section6는  
"우리가 만든 MVC 구조가 실제 스프링 MVC 안에서 어떻게 구현되어 있는지 확인하는 과정"이다.