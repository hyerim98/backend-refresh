package hello.springmvc.response;

import hello.springmvc.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(HttpServletResponse response) throws IOException {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(HttpServletResponse response) throws IOException {
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData data = new HelloData();
        data.setUsername("userA");
        data.setAge(20);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK) // 어노테이션이기 때문에 동적으로 변경 불가 프로그램 조건에 따라 동적으로 변경하려면 ResponseEntity 사용해야함(responseBodyJsonV1() 참고)
    @ResponseBody // @Controller 대신에 @RestController를 사용하면 @ResponseBody는 사용안해도 됨
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData data = new HelloData();
        data.setUsername("userA");
        data.setAge(20);
        return data;
    }
}
