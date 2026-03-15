# 🌐 Spring MVC1 - 웹 MVC 핵심 구조

김영한님의  
**Spring MVC 1편 - 백엔드 웹 개발 핵심 기술** 강의를 학습하며  
웹 애플리케이션의 동작 구조와 Spring MVC의 핵심 원리를 정리했습니다.

---

# 📚 Study Structure
- **notes** : 개념 및 동작 원리 정리
- **practice** : 강의 코드 기반 구현 흐름 정리

---

# 📘 notes (개념 정리)

웹 애플리케이션의 동작 구조와  
Spring MVC 프레임워크의 내부 동작 원리를 정리했습니다.

---

## 1. 웹 애플리케이션 이해

- 웹 서버(Web Server)
- 웹 애플리케이션 서버(WAS)
- HTTP 요청 / 응답 구조
- 정적 리소스 vs 동적 리소스

---

## 2. 서블릿(Servlet)

Java 기반 웹 애플리케이션의 핵심 기술

- HttpServlet
- HTTP 요청 데이터 처리
- HTTP 응답 데이터 처리

---

## 3. 서블릿, JSP, MVC 패턴

웹 애플리케이션 구조 발전 과정

```
Servlet
 ↓
JSP
 ↓
MVC Pattern
```

- Model
- View
- Controller

역할 분리를 통해 유지보수성을 향상시키는 구조

---

## 4. MVC 프레임워크 만들기

Front Controller 패턴을 기반으로  
MVC 프레임워크를 직접 구현합니다.

주요 개념

- Front Controller
- Handler Mapping
- View Resolver
- Controller 인터페이스

---

## 5. Spring MVC 구조 이해

Spring MVC 내부 동작 구조

```
Client
 ↓
DispatcherServlet
 ↓
HandlerMapping
 ↓
HandlerAdapter
 ↓
Controller
 ↓
ViewResolver
 ↓
View
```

Spring MVC가 요청을 처리하는 전체 흐름을 이해합니다.

---

## 6. Spring MVC 기본 동작

Spring MVC에서 제공하는 기본 기능

- @Controller
- @RequestMapping
- Model
- View
- ViewResolver

---

# 📗 practice (구현 정리)

강의에서 구현한 MVC 구조를  
버전별로 발전시키며 정리했습니다.

---

## v1 - 단순 컨트롤러 구조

각 컨트롤러가 직접 요청을 처리하는 구조

```
Client
 ↓
Controller
 ↓
View
```

---

## v2 - Front Controller 도입

공통 요청 처리를 위한 Front Controller 적용

```
Client
 ↓
FrontController
 ↓
Controller
 ↓
View
```

---

## v3 - Model 도입

Controller가 Model 데이터를 반환하도록 개선

```
Controller
 ↓
ModelView
 ↓
View
```

---

## v4 - View 분리

View 경로를 분리하여 Controller 코드 간결화

---

## v5 - Handler Adapter 도입

다양한 Controller 방식을 지원하는 구조

```
Dispatcher
 ↓
HandlerMapping
 ↓
HandlerAdapter
 ↓
Controller
```

---

# 🎯 Study Goal

- 웹 애플리케이션 동작 구조 이해
- Servlet 기반 요청 처리 이해
- MVC 패턴 이해
- Front Controller 패턴 이해
- Spring MVC 내부 구조 이해