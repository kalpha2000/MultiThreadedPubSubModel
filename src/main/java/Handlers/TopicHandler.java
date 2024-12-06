package Handlers;

import Components.Subsriber;
import Components.Topic;
import Service.Subscriber;
import lombok.Data;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

@Data
public class TopicHandler {
    private Topic topic;
    private Map<String, Worker> subscriberWokers;

    public TopicHandler(@NonNull Topic topic) {
        this.topic = topic;
        this.subscriberWokers = new HashMap<>();
    }

    public void publish() {
        for(TopicSubsriber topicSubsriber: topic.getTopicSubscribers()) {
            startWorker(topicSubsriber);
        }
    }

    public void startWorker(@NonNull TopicSubsriber topicSubsriber) {
        String id = topicSubsriber.getSubsriber().getId();
        if(!subscriberWokers.containsKey(id)) {
            Worker newWorker = new Worker(topic, topicSubsriber);
            subscriberWokers.put(id, newWorker);
            new Thread(newWorker).start();
        }
        Worker worker = subscriberWokers.get(id);
        worker.wakeUp();
    }

}
