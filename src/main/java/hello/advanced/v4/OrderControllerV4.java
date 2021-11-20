package hello.advanced.v4;

import hello.advanced.trace.Template.AbstractTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final LogTrace trace; // HelloTraceV1을 의존관계 주입받는다.

    @GetMapping("/v4/request")
    public String request(String itemId) {
        AbstractTemplate<String> Template = new AbstractTemplate<String>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };

        return Template.excute("OrderController.request()");
    }
}
