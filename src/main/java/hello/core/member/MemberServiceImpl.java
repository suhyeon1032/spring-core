package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired // 자동 의존관계 주입 // ac.getBean(MemberRepository.class) 동작
    public MemberServiceImpl(MemberRepository memberRepository) { // <- 생성자를 만들고 생성자를 통해서 memberRepository 에 구현제가 뭐가 들어갈지 선택한다.
        this.memberRepository = memberRepository;  // AppConfig에서 MemoryMemberRepository를 넣어주었다.
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // MemoryMemberRepository 두 번 호출 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
