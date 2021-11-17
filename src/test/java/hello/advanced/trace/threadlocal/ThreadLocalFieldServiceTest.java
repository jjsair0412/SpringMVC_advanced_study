package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalFieldServiceTest {

    private ThreadLocalFieldService fieldService = new ThreadLocalFieldService();

    @Test
    void field(){

        /***
         * 결과 :
         * 저장 name = userA -> nameStore =null
         * 저장 name = userB -> nameStore =null
         * 조회 nameStore = userA
         * 조회 nameStore = userB
         *
         * 스레드로컬을 적용하고나면, 동시성문제가 발생하지 않는다.
         * 처음에 저장할때 nameStore에 null이 나오는 이유는
         * 각 스레드들만의 특별한 저장공간이 생겼기 때문이다.
         */

        log.info("main Start");
        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread - A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread - B");

        threadA.start();
        sleep(100);
        threadB.start();

        sleep(3000);
        log.info("main exit");
    }

    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
