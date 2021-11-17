package hello.advanced.trace;

public class TraceStatus { // 로그의 상태정보를 나타낸다.

    /**
     * 결과 예시
     * [796bccd9] OrderController.request() //로그 시작
     * [796bccd9] OrderController.request() time=1016ms //로그 종료
     *
     * 시작시간과 종료시간등을 출력시켜야 하기 때문에 , 로그가 시작할때의 상태정보를 가지고 있는 TraceStatus가 필요하다.
     *
     * traceId : 내부에 트랜잭션ID와 level을 가지고 있다.
     * startTimeMs : 로그 시작시간이다. 로그 종료시 이 시작 시간을 기준으로 시작~종료까지 전체 수행시간을 구할 수 있다.
     * message : 시작시 사용한 메시지이다. 이후 로그 종료시에도 이 메시지를 사용해서 출력한다.
     */

    private TraceId traceId;
    private Long startTimeMs;
    private String message;

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
