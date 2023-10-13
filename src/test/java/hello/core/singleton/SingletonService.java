package hello.core.singleton;

public class SingletonService {

    // 자기 자신을 내부의 private으로 가지고 있다.
    // 이 때 static으로 가지고 있기 때문에 class 레벨에 올라가기 때문에 딱 하나만 존재하게 된다.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
