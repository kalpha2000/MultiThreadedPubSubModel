package Components;


import java.util.concurrent.atomic.AtomicInteger;

public class Subsriber {

    public AtomicInteger offset;
    public void consume(Message message) {
        System.out.printf("Consuming message : {}", message.getMsg());
    }
}
