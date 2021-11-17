package hello.advanced.trace.hellotrace;


import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV1 {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    public TraceStatus begin(String message) { // 로그 시작
        // [796bccd9] OrderController.request()  얘가 생성됨
        TraceId traceId = new TraceId(); // begin이 호출될때마다 traceId를 새로만듦
        long startTimeMs = System.currentTimeMillis();
        // 로그 출력
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);

    }

    public void end(TraceStatus status) { // 로그 종료 ( 정상흐름시 얘 호출 )
        // [796bccd9] OrderController.request() time=1016ms  얘가 생성됨
        complete(status, null);
    }

    public void exception(TraceStatus status, Exception e) { // 예외발생했을때 호출
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
        }
    }


    // level=0
    // level=1 |-->
    // level=2 |   -->
    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder(); // StringBuilder기 때문에 메모리 재사용 ( 기존거에 추가 가능 )
        for (int i = 0; i < level; i++) {
            sb.append( (i == level - 1) ? "|" + prefix : "| ");
        }
        return sb.toString();
    }

}
