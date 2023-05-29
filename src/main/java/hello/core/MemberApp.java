package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService(); // 기존에는 appConfig. 에서 직접 찾아왔지만 이젠 스프링컨테이너를 통해서 찾아온다.
//        MemberService memberService = new MemberServiceImpl();

        ApplicationContext applicationContex = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContex.getBean("memberService", MemberService.class); //memberService : 이름 , MemberService.class : 타입

        Member member = new Member(1L, "memberA", Grade.VIP); //1L = long타입
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
