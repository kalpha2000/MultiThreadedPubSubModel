package Service;

import Components.Message;
import Components.Subsriber;
import lombok.Data;
import lombok.NonNull;

@Data
public class Subscriber implements Subsriber {
    private String id;
    private Integer sleepTimeInMillis;

    public Subscriber(@NonNull String id, @NonNull Integer sleepTimeInMillis) {
        this.id = id;
        this.sleepTimeInMillis = sleepTimeInMillis;
    }

    @Override
    public void consume(Message message) throws InterruptedException {
        System.out.println("Subscriber: " + id + " started consuming: " + message.getMsg());
        Thread.sleep(sleepTimeInMillis);
        System.out.println("Subscriber: " + id + " done consuming: " + message.getMsg());
    }
}
