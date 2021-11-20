package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {


    @Test
    void strategyV1(){
        Strategy strategy = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategy);
        contextV1.excute();

        Strategy strategy2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategy2);
        contextV2.excute();
    }


    @Test
    void strategyV0(){
        logic1();
        logic2();
    }


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
}
