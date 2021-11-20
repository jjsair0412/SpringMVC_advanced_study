package hello.advanced.trace.Template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

// <T> 로 제네릭을 사용한다,
// 상황에따라 반환타입이 달라지기 때문에 제네릭으로 반환타입을 설정해주었다.
public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace){
        this.trace = trace;
    }

    public T excute(String message){
        TraceStatus status = null;
        try{
            status = trace.begin(message);
            // 로직호출
            T result = call(); // call을 구현할때 만들어준다.

            trace.end(status);
            return result;
        }catch (Exception e){
            trace.exception(status,e);
            throw e;
        }
    }

    protected abstract T call();

}
