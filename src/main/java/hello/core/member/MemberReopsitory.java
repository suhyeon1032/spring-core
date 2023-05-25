package hello.core.member;


//인터페이스
public interface MemberReopsitory {

    void save(Member member);

    Member findById(Long memberId);
}
