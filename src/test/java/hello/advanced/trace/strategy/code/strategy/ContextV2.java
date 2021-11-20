package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;


/**
 * 전략 ( Strategy ) 를 파라미터로 넘겨받아서 사용하는 방식
 */
@Slf4j
public class ContextV2 {

    public void excute(Strategy strategy){
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        strategy.call(); // 핵심로직
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
