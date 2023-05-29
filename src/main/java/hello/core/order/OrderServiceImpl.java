package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberReopsitory;

public class OrderServiceImpl implements OrderService { // implements OrderService => 의존하고 있다.

    private final MemberReopsitory memberReopsitory; //memberReopsitory와 discountPolicy를 참조(의존)하고있다.
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 1. 인터페이스에만 의존하는게 아닌 구체 클래스에도 의존하고 있다
//    따라서 FixDiscountPolicy 를 RateDiscountPolicy 로 변경하는 순간 OrderServiceImpl 의 소스 코드도 함께 변경해야 한다
    private final DiscountPolicy discountPolicy; // 2. 구체에 의존하지 않고 추상화인 인터페이스에만 의존한다. // 하지만 NullPointerException이 터진다.

    // final이 되어 있으면 기본적으로 생성자를 할당 해야한다.
    public OrderServiceImpl(MemberReopsitory memberReopsitory, DiscountPolicy discountPolicy) {
        this.memberReopsitory = memberReopsitory;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberReopsitory.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
