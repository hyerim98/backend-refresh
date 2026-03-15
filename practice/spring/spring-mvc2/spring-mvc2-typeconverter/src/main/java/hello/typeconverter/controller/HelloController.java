package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    // StringToIpPortConverter 사용하여 String을 IpPort 객체로 변환하여 파라미터로 던져주는 것을 확인
    //@RequestParam을 처리하는 ArgumentResolver인 RequestParamMethodArgumentResolver에서 ConversionService를 사용해서 타입을 변환
    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {
        log.info("ipPort IP = " + ipPort.getIp());
        log.info("ipPort PORT = " + ipPort.getPort());
        return "ok";
    }

    // MyNumberFormatter를 사용하여 문자를 숫자로 숫자를 문자로 변환
    @GetMapping("/char")
    public String character(@RequestParam Integer data) {
        log.info("number={}", data);
        return "ok";
    }
}
