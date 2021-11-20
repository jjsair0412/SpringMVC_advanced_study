package hello.advanced.trace.callback;

public interface TraceCallback<T> {
    T call(); // 제네릭으로 반환타입 다를수도 있는거 대응
}
