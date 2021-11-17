package hello.advanced;

import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoGTraceConfig {

//    @Bean
//    public LogTrace logTraceConfig(){
//        return new FieldLogTrace();
//    }

    @Bean
    public LogTrace logTraceConfig(){
        return new ThreadLocalLogTrace(); // 스레드로컬로 변환
    }
}
