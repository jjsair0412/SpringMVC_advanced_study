package hello.advanced.trace.threadlocal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalFieldService {

    // 스레드로컬로 객체를 만들어주면 된다. <> 안에는 데이터타입이 들어간다.
    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name){
        log.info("저장 name = {} -> nameStore ={}",name,nameStore.get());
        nameStore.set(name); // 저장할때는 set을 사용하면 된다.
        sleep(1000); // 1초동안 행동
        log.info("조회 nameStore = {}",nameStore.get());
        return nameStore.get(); // 꺼낼때는 get을 사용하면 된다.
    }

    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
