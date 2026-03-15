package hello.servlet.section6_mvc_basic.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // 스프링 빈 등록
public class SpringMemberFormControllerV1 {
    @RequestMapping("/springmvc/v1/members/new-form") // 요청 정보를 매핑(핸들러 매핑과 핸들러 어댑터)
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
