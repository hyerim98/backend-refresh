package hello.core.section6_singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatelessServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatelessService statelessService1 = ac.getBean("statelessService", StatelessService.class);
        StatelessService statelessService2 = ac.getBean("statelessService", StatelessService.class);

        int priceA = statelessService1.order("itemA", 10000);
        int priceB = statelessService2.order("itemB", 20000);

        System.out.println("priceA=" + priceA);
        System.out.println("priceB=" + priceB);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }
}
