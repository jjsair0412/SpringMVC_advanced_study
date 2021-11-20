package hello.advanced.trace.callback;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public class TraceTemplate {
    private final LogTrace trace;

    public TraceTemplate(LogTrace trace){
        this.trace = trace;
    }

    public <T> T excute(String message, TraceCallback<T> callback){
        TraceStatus status = null;
        try{
            status = trace.begin(message);
            // 로직호출
            T result = callback.call(); // call을 구현할때 만들어준다.

            trace.end(status);
            return result;
        }catch (Exception e){
            trace.exception(status,e);
            throw e;
        }
    }
}
