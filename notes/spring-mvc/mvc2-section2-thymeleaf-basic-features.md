# Section 02 - 타임리프 기본 기능

> 타임리프는 서버 사이드 템플릿 엔진이다.  
> Model에 담긴 데이터를 HTML에 자연스럽게 렌더링한다.

---

# 1️⃣ 타임리프 특징

- 서버 사이드 렌더링
- HTML을 그대로 유지하는 Natural Template
- Spring과 강한 통합
- 폼 바인딩 지원

---

# 2️⃣ 기본 표현식

## 2.1 변수 표현식

${...}

- Model에 담긴 데이터 접근
- 가장 기본 표현식

---

## 2.2 선택 변수 표현식

*{...}

- th:object와 함께 사용
- 객체 기준으로 필드 접근

---

## 2.3 메시지 표현식

#{...}

- 국제화 메시지 처리

---

## 2.4 링크(URL) 표현식

@{...}

- 컨텍스트 경로 자동 처리
- 쿼리 파라미터 자동 생성 가능
- 예시
```
<li><a th:href="@{/hello(param1=${param1}, param2=${param2})}">hello queryparam</a></li>
<li><a th:href="@{/hello/{param1}/{param2}(param1=${param1}, param2=${param2})}">path variable</a></li>
```

---

# 3️⃣ 주요 속성

## 3.1 텍스트 출력

th:text
- HTML Escape 적용

th:utext
- Escape 미적용 (주의 필요)

---

## 3.2 반복문

th:each

- 컬렉션 반복
- 상태 변수 사용 가능

---

## 3.3 조건문

th:if  
th:unless

- 조건에 따라 렌더링 여부 결정

---

## 3.4 속성 값 변경

th:href  
th:value  
th:class  
th:checked

- 기존 HTML 속성을 동적으로 변경

---

# 4️⃣ 자바스크립트 인라인

th:inline="javascript"

- JS 코드 내부에서 안전하게 값 사용
- 서버 데이터를 JS 변수로 주입
- JSON 자동 변환 지원

📌 서버에서 완성된 JS 코드가 브라우저로 전달된다.

---

# 5️⃣ 템플릿 조각 (Fragment)

th:fragment

- HTML 일부를 재사용 가능

th:insert  
th:replace

- 조각을 삽입하거나 교체

📌 공통 헤더/푸터 관리에 사용

---

# 6️⃣ 템플릿 레이아웃

- 공통 레이아웃 정의
- 변경 영역만 치환
- 페이지 구조 통일

📌 서버에서 하나의 완성된 HTML로 조립된다.

---

# 🔥 섹션2 핵심 정리

타임리프 기본 기능은

✔ Model 데이터를 HTML로 렌더링하는 방법  
✔ 서버에서 화면을 완성하는 구조  
✔ 템플릿 재사용과 레이아웃 구성

을 이해하는 단계이다.