package hello.itemservice.web.validation;

import hello.itemservice.domain.item.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 성공 요청: 성공
 * 실패 요청: JSON을 객체로 생성하는 것 자체가 실패함 -> 객체를 만들지 못하기 때문에 컨트롤러 자체가 호출되지 않고 그 전에 예외가 발생한다 물론 Validator도 실행되지 않는다
 * 검증 오류 요청: JSON을 객체로 생성하는 것은 성공했고, 검증에서 실패함
 */

/**
 * @ModelAttribute
    * HTTP 요청 파라미터(URL 쿼리 스트링, POST Form) 다룰 때 사용
    * 필드 단위로 정교하게 바인딩 적용
    * 특정 필드가 바인딩 되지 않아도 나머지 필드는 정상 바인딩 되고, Validator를 사용한 검증도 적용할 수 있다
 * @RequestBody
    * HTTP Body의 데이터를 객체로 변환할 때 사용, 주로 API JSON 요청을 다룰 때 사용
    * HttpMessageConverter 단계에서 JSON 데이터를 객체로 변경하지 못하면 이후 단계 자체가 진행되지 않고 예외 발생
    * 컨트롤러도 호출되지 않고, Validator도 적용할 수 없다
 */
@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {
    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) {
        log.info("API 컨트롤러 호출");

        if(bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("성공 로직 실행");
        return form;
    }
}
