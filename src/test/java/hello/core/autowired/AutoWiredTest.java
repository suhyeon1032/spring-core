package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static  class TestBean{

        // (required = false)를 할 경우 의존관계가 없으면 수정자 메서드 자체가 호출 안됨
        @Autowired(required = false) // true로 할 경우 예외발생 -> member가 스프링 빈으로 등록되지 않기 때문
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // @Nullable 작성 시 호출은 가능하되 null로 호출
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // Optional<Member> 스프링 빈이 없으면 Optional.empty 호출 // 값이 있으면 Optional<Member> 안에 감싸져 나옴
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }

}
