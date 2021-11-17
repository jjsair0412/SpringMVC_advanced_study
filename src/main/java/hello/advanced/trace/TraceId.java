package hello.advanced.trace;

import java.util.UUID;

public class TraceId {
    private String id; // 트랜잭션마다 가지는 고유의 ID ( 트랜잭션은 하나의 HTTP 요청 )
    private int level; // 깊이를 알 수 있게끔 하는 level

    public TraceId(){
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level){
        this.id = id;
        this.level =level;
    }

    private String createId(){
        return UUID.randomUUID().toString().substring(0, 8); // 기존 UUID는 너무 길기때문에 맨 앞자리 String 8글자만 사용한다.
    }

    public TraceId createNextId(){ // 다음 id값을 편리하게 계산해주는 메서드
        return new TraceId(id,level+1);  // 기존 ID값은 동일하지만 level은(깊이는) 하나씩 증가해야하기때문에 필요하다.
    }

    public TraceId createPreviousId(){ // nextId와 비슷한 맥락이다. 이전단계 깊이를 알아야 하기 때문에 id는 동일하고 level만 줄어든다.
        return new TraceId(id,level-1);
    }

    public boolean isFirstLevel(){ // 첫번째 레벨인지 여부를 알 수 있는 메서드
        return level==0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
