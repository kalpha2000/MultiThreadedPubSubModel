package Handlers;

import Components.Topic;
import lombok.Data;
import lombok.NonNull;
import lombok.SneakyThrows;

@Data
public class Worker implements Runnable{

    private Topic topic;
    private final TopicSubsriber topicSubsriber;

    public Worker(@NonNull Topic topic, @NonNull TopicSubsriber topicSubsriber) {
        this.topic = topic;
        this.topicSubsriber = topicSubsriber;
    }


    @SneakyThrows
    @Override
    public void run(){
        synchronized (topicSubsriber) {
            do {
                int offset = topicSubsriber.getOffset().get();
                while (offset >= topic.getMessages().size()) {
                    topicSubsriber.wait();
                }
                System.out.println(Thread.currentThread().getName() + "-" + topicSubsriber.getSubsriber().getId());

                topicSubsriber.getSubsriber().consume(topic.getMessages().get(offset));

                topicSubsriber.getOffset().compareAndSet(offset, offset + 1);
            } while (true);
        }
    }

    public void wakeUp() {
        synchronized (topicSubsriber) {
            topicSubsriber.notify();
        }
    }
}
