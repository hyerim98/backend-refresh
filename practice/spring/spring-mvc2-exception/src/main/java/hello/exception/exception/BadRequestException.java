package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResponseStatusExceptionResolver
    * 예외에 따라서 HTTP 상태 코드를 지정해주는 역할
 */
//@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "잘못된 요청 오류")
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.bad") // messages.properties에서 메시지 불러오기
public class BadRequestException extends RuntimeException{
}
