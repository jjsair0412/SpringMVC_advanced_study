package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.template.Callback;
import hello.advanced.trace.strategy.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

    /**
     * 템플릿 콜백 패턴
     */
    @Test
    void callbackV1(){
        TimeLogTemplate template = new TimeLogTemplate();
        template.excute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스로직 1 실행");
            }
        });

        template.excute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스로직 2 실행");
            }
        });
    }
    /**
     * 템플릿 콜백 패턴 - 람다적용
     */
    @Test
    void callbackV2(){
        TimeLogTemplate template = new TimeLogTemplate();
        template.excute(()->log.info("비즈니스로직 1 실행"));

        TimeLogTemplate template2 = new TimeLogTemplate();
        template2.excute(()->log.info("비즈니스로직 1 실행"));
    }
}
