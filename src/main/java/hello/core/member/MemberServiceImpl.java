package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberReopsitory memberReopsitory;

    public MemberServiceImpl(MemberReopsitory memberReopsitory) { // <- 생성자를 만들고 생성자를 통해서 memberReopsitory 에 구현제가 뭐가 들어갈지 선택한다.
        this.memberReopsitory = memberReopsitory;  // AppCfig에서 MemoryMemberRepository를 넣어주었다.
    }

    @Override
    public void join(Member member) {
        memberReopsitory.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberReopsitory.findById(memberId);
    }
}
