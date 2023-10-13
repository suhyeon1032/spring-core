package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StateFulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFulService statefulService1 = ac.getBean(StateFulService.class);
        StateFulService statefulService2 = ac.getBean(StateFulService.class);

        // ThreadA : A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB : B사용자 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA : 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); // 10000이 출력 되어야 하지만 price = 20000 출력
        // 같은 인스턴스를 사용하기 때문

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StateFulService stateFulService() {
            return new StateFulService();
        }
    }
}