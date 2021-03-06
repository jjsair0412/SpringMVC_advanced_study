package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field(){

        /***
         * logic 내부에서는 저장할때 1초의 시간이 걸린다.
         * 그래서 2초동안 기다렸다가 다음스레드가 공유영역을 접근하게끔하면 동시성문제가 발생하지 않는다.
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
//        sleep(2000); // 동시성문제가 발생하지 않는다.
        sleep(100); // 동시성문제가 발생한다.
                          // 왜냐하면 첫번째 스레드가 저장이 완료되기까지의 시간은 1초인데, 0.1초만에 그다음 스레드가 저장되기때문이다.
        threadB.start();

        sleep(3000); // 메인스레드 종료 대기
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
