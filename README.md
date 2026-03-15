# backend-refresh

## 🔹 목적
- 스프링, 자바, 네트워크, 데이터베이스 기초부터 실습까지
- notes / practice / errors / references / tools 구조화

---

## 🔹 폴더 구조
- `notes/`         : 개념 및 동작 원리 정리
- `practice/`      : 코드 실습 + 실험 기록
  - `original/`  : 강의 코드 그대로
  - `experiment/`: 내 실험 코드
  - `README.md`  : 강의 코드 기반 구현 흐름 정리
- `errors/`        : 삽질 기록 (발생 상황, 원인, 해결법)
- `references/`    : 책, 블로그, 외부 자료 링크
- `tools/`         : IDE, Git, 터미널 등 실무 도구 활용 팁
  - Git, IntelliJ, Terminal 등 폴더별 정리

---

## 🔹 학습 순서

### 1️⃣ 스프링
🌱 **스프링 핵심 원리** 🌱
  - 주요 학습 내용
    - 객체 지향 설계
    - SOLID 원칙
    - DI (Dependency Injection)
    - Spring Container
    - Bean 관리
    - Singleton Container
    - Component Scan
    - 의존관계 자동 주입
    - Bean 생명주기

🌐 **스프링 MVC1** 🌐
- 주요 학습 내용
  - Servlet
  - HTTP 요청 처리
  - MVC 패턴
  - Front Controller 패턴
  - MVC Framework 직접 구현
  - Spring MVC 구조 이해

🧩 **스프링 MVC2** 🧩
- 주요 학습 내용

#### View Template

- Thymeleaf 기본 기능
- Template Layout
- Template Fragment

#### Form 처리

- Form 데이터 바인딩
- Validation 처리
- Bean Validation

#### 웹 애플리케이션 기능

- 로그인 처리
- Session 관리
- Filter / Interceptor

#### 예외 처리

- 오류 페이지 처리
- API 예외 처리
- ExceptionResolver

#### 데이터 처리

- TypeConverter
- Formatter

#### 파일 처리

- Multipart 요청
- 파일 업로드 처리


### 2️⃣ 자바
- 필요한 순간만 찾아서 실습
- practice README에 실험 기록
- notes에 이해 정리

### 3️⃣ 네트워크
- 스프링 실습 중간중간 필요 시 학습
- practice에 실험 코드 + README
- notes에 개념 정리

### 4️⃣ 데이터베이스 / MyBatis
- 실습 + notes 정리
- 트랜잭션, 매핑, 쿼리 등 실험

### 5️⃣ tools
- Git, IntelliJ, Terminal 활용법 정리
- 단축키, 설정, 명령어, 실무 팁
- 공부 중 발견 즉시 기록

---

## 🔹 학습 방법
1. 강의 시청
2. 코드 타이핑 / 따라쓰기
3. 실험 (구현체 변경, new 제거, 일부러 깨보기 등)
4. practice README 기록 (실험 목적/방법/관찰)
5. notes에 개념 + 내 언어로 정리
6. errors에 삽질 기록 
7. tools에 IDE/Git/터미널 팁 기록

---

## 🔹 주의 사항
- notes와 practice README 절대 섞지 않기
- practice 코드: original vs experiment 분리
- errors 폴더는 처음엔 flat, 많아지면 주제별로 폴더 확장
- tools 폴더는 실습과 연계되지 않더라도 즉시 기록
- 하루 단위가 아니라 섹션 단위로 기록하는 것을 추천

---

## 🔹 업데이트 기록 (옵션)
- 2026-01-26: 초기 구조 작성
- 2026-02-01: 섹션1 실습 완료 후 notes/README 업데이트