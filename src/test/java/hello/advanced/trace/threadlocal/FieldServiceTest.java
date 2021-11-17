package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field(){
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
        sleep(2000); // 동시성문제가 발생하지 않는다.
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
