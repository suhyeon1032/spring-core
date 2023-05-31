package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberReopsitory;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByTypeDuplicate() {
//        MemberReopsitory bean = ac.getBean(MemberReopsitory.class);
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberReopsitory.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByName() {
        MemberReopsitory memberRepository = ac.getBean("memberRepository1", MemberReopsitory.class);
        assertThat(memberRepository).isInstanceOf(MemberReopsitory.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberReopsitory> beansOfType = ac.getBeansOfType(MemberReopsitory.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }


    @Configuration
    static class SameBeanConfig {
        // static = class 안에 class를 쓰는 것은 이 안에서만 사용하겠다는 의미

        @Bean
        public MemberReopsitory memberRepository1() {
            return new MemoryMemberRepository();
            // class타입이 같은 Bean이 있을 수 있다(Bean의 이름이 달라야한다)
        }

        @Bean
        public MemberReopsitory memberRepository2() {
            return new MemoryMemberRepository();
        }

    }

}
