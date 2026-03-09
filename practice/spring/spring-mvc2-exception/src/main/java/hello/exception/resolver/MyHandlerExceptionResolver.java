package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * 컨트롤러 밖으로 던져진 예외를 해결, 동작 방식을 변경
 * 서블릿에서 상태 코드에 따른 오류를 처리하도록 위임 이후 WAS는 서블릿 오류 페이지를 찾아서 내부 호출(스프링 부트가 기본으로 설정한 /error가 호출됨)
 */
@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if(ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView(); // 정상 흐름으로 서블릿이 리턴
            }

        } catch (IOException e) {
            log.error("resolver ex", e);
        }
        return null;
    }
}
