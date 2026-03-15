# 🌱 Spring Core - 기본편

김영한님의 **스프링 핵심 원리 - 기본편** 강의를 학습하며  
Spring의 핵심 개념과 DI 컨테이너 구조를 정리했습니다.

---

# 📘 notes (개념 정리)

Spring 핵심 개념과 동작 원리를 정리했습니다.

### 1. 객체 지향 설계와 스프링

- 객체 지향 프로그래밍
- SOLID 원칙
- 다형성
- 역할과 구현 분리

---

### 2. 스프링 핵심 원리 이해

- 회원 도메인 설계
- 주문 도메인 설계
- 비즈니스 요구사항 구현

객체 지향 설계를 기반으로 애플리케이션을 설계하는 방법을 학습합니다.

---

### 3. DI (Dependency Injection)

의존관계 주입을 통해  
객체 간 결합도를 낮추는 구조를 학습합니다.

- 생성자 주입
- 의존관계 주입 구조
- DIP 적용

---

### 4. 스프링 컨테이너

Spring이 객체를 관리하는 방식

- ApplicationContext
- Bean 등록
- Bean 조회

---

### 5. Bean 관리

스프링 컨테이너에서 Bean을 관리하는 방식

- BeanDefinition
- Bean 스코프
- 싱글톤 패턴
- 싱글톤 컨테이너

---

### 6. 컴포넌트 스캔

Spring이 자동으로 Bean을 등록하는 기능

- @Component
- @ComponentScan
- @Autowired

---

### 7. 의존관계 자동 주입

Spring이 자동으로 의존관계를 주입하는 방식

- 생성자 주입
- 필드 주입
- 수정자 주입

---

### 8. Bean 생명주기

Spring Bean 생성 및 소멸 과정

- 초기화 메서드
- 종료 메서드
- Bean 생명주기 관리

---

# 📗 practice (코드 구현)

강의에서 구현한 코드 흐름을 기반으로  
Spring의 핵심 구조를 실습하며 정리했습니다.

---

### 회원 도메인 구현

회원 관리 기능 구현

```
회원 가입
회원 조회
```

---

### 주문 도메인 구현

주문 생성 및 할인 정책 적용

```
주문 생성
할인 정책 적용
주문 결과 반환
```

---

### DI 적용

객체 간 의존관계를 외부에서 주입하는 구조

```
AppConfig
 ↓
객체 생성
 ↓
의존관계 주입
```

---

### 스프링 컨테이너 적용

Spring Container를 활용하여  
객체를 관리하는 방식

```
ApplicationContext
 ↓
Bean 등록
 ↓
Bean 조회
```

---

### 컴포넌트 스캔 적용

Spring이 자동으로 Bean을 등록

```
@Component
@ComponentScan
@Autowired
```

---

# 🎯 Study Goal

- 객체 지향 설계 이해
- DI(Dependency Injection) 이해
- Spring Container 구조 이해
- Bean 관리 방식 이해
- 컴포넌트 스캔 동작 원리 이해

Spring Framework의 핵심 원리를 이해하는 것을 목표로 합니다.