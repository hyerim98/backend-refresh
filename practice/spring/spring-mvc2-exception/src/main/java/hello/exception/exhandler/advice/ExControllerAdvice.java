package hello.exception.exhandler.advice;

import hello.exception.api.ApiExceptionV3Controller;
import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @RestControllerAdvice
    * 대상을 지정하지 않으면 모든 컨트롤러에 적용
 */
@Slf4j
@RestControllerAdvice(assignableTypes = {ApiExceptionV3Controller.class}) // ApiExceptionV3Controller에만 적용되도록 설정
public class ExControllerAdvice {
    // IllegalArgumentException 또는 그 하위 자식 클래스를 모두 처리
    // @ResponseStatus 사용하여 응답코드 설정(사용안하면 200으로 처리 -> ExceptionHandler는 WAS 전에 컨트롤러에서 예외를 해결함으로 정상 처리로 보이는게 맞음)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandle(IllegalArgumentException e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    // ResponseEntity를 사용해서 HTTP 메시지 바디에 직접 응답(HTTP 컨버터 사용)
    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandle(UserException e) {
        log.error("[exceptionHandle] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    // 다양한 예외를 한번에 처리
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }
}
