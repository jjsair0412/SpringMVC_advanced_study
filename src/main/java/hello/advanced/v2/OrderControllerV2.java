package hello.advanced.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace; // HelloTraceV1을 의존관계 주입받는다.

    @GetMapping("/v2/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try { // 정상흐름일때 try
            status = trace.begin("OrderController.request()"); // 로그 시작
            orderService.orderItem(status.getTraceId(),itemId);
            trace.end(status); // 정상실행 종료시 end 호출
        } catch (Exception e) { // 예외발생시 catch로
            trace.exception(status, e); // exception 메서드를 호출
            throw e; // 예외를 꼭 다시 던져주어야 한다.
        }
        return "ok";
    }
}
