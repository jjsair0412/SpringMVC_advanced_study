package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import org.junit.jupiter.api.Test;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestFactory;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    /***
     여기서의 부가기능은 시작을 체크해주는 기능이다.
     변하는부분은 비즈니스로직이다.
     또한 변하지않는부분은 시간을체크해주는 부분이다.
     그런데 이러한 공통적인 부가기능부분을 메서드로 추출해서 사용하기는 애매하다.
     왜냐하면 핵심로직이 중간에 껴잇기때문이다.
     이럴때 템플릿메서드 패턴을 사용하게 된다.
     템플릿메서드 패턴을 사용하면 핵심기능과 부가기능을 효율적이게 나누어 사용할 수 있다.
     **/
    private void logic1() {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직 1 실행"); // 핵심로직
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);

    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직 2 실행"); // 핵심로직
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime2 = endTime - startTime;
        log.info("resultTime2={}", resultTime2);
    }

    /**
     * 템플릿 메서드 패턴 적용
     */
    @Test
    void templateMethodV1(){
        AbstractTemplate template = new SubClassLogic1();
        template.excute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.excute();
    }


    // 익명클래스를 사용해서 구현체를 계속만들어야하는 단점 해결

    /**
     * 걸과 :
     * TemplateMethodTest - 비즈니스 로직 1 실행
     * AbstractTemplate - resultTime=4
     * TemplateMethodTest - 클래스 이름 1 =class hello.advanced.trace.template.TemplateMethodTest$1
     * TemplateMethodTest - 비즈니스 로직 2 실행
     * AbstractTemplate - resultTime=1
     * TemplateMethodTest - 클래스이름 2 =class hello.advanced.trace.template.TemplateMethodTest$2
     */
    @Test
    void TemplateMethodV2(){
        AbstractTemplate template = new AbstractTemplate(){
            @Override
            protected void call() {
                log.info("비즈니스 로직 1 실행");
            }
        };
        template.excute();
        log.info("클래스 이름 1 ={}",template.getClass());

        AbstractTemplate template2 = new AbstractTemplate(){
            @Override
            protected void call() {
                log.info("비즈니스 로직 2 실행");
            }
        };
        template2.excute();
        log.info("클래스이름 2 ={}",template2.getClass());
    }
}
