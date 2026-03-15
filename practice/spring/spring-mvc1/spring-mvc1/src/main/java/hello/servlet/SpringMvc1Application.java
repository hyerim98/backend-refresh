package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // 스캔하여 서블릿 자동으로 등록해주는 어노테이션
@SpringBootApplication
public class SpringMvc1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvc1Application.class, args);
    }

}
