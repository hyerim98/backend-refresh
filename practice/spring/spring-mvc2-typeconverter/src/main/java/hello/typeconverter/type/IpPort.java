package hello.typeconverter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode // 값이 같은 객체를 같은 것으로 판단(주소가 아닌 주소안에 데이터가 같으면 같은 것으로 판단)
public class IpPort {
    private String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
