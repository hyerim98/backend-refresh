package hello.core.section10_beanscope.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 학습 기록용으로 섹션별 코드 다 보존하여 빈들이 충돌되는 문제 발생으로 웹 스코프 테스트용 전용 부트 클래스 생성
@SpringBootApplication(
        scanBasePackages = "hello.core.section10_beanscope"
)
public class WebScopeApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebScopeApplication.class, args);
    }
}
