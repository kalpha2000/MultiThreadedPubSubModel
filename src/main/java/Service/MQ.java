package Service;

import Components.Message;
import Components.Subsriber;
import Components.Topic;
import Handlers.TopicHandler;
import Handlers.TopicSubsriber;
import lombok.Data;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class MQ {
    private Map<String, TopicHandler> topicHandlers;

    public MQ() {
        this.topicHandlers = new HashMap<>();
    }

    public Topic createTopic(@NonNull String topicName) {
        Topic topic = new Topic(topicName, UUID.randomUUID().toString());
        TopicHandler topicHandler = new TopicHandler(topic);
        topicHandlers.put(topic.getTopicId(), topicHandler);
        System.out.println("Created Topic : " + topic.getTopicName());
        return topic;
    }

    public void subscribe(@NonNull Topic topic, @NonNull Subsriber subsriber) {
        TopicSubsriber topicSubsriber = new TopicSubsriber(subsriber);
        topic.addSubsriber(topicSubsriber);
        System.out.println(subsriber.getId() + " subscribed to topic: " + topic.getTopicName());
        new Thread(() -> topicHandlers.get(topic.getTopicId()).startWorker(topicSubsriber)).start();
    }

    public void publishMessage(@NonNull Topic topic, @NonNull Message message) {
        topic.addMessages(message);
        System.out.println(message.getMsg() + " published to topic: " + topic.getTopicName());
        new Thread(() -> topicHandlers.get(topic.getTopicId()).publish()).start();
    }

    public void resetOffset(@NonNull Topic topic, @NonNull Subscriber subscriber, @NonNull Integer newOffset) {
        for(TopicSubsriber topicSubsriber:topic.getTopicSubscribers()) {
            if(topicSubsriber.getSubsriber().equals(subscriber)) {
                topicSubsriber.getOffset().set(newOffset);
                System.out.println(subscriber.getId() + " offset reset to " + newOffset);
                new Thread(() -> topicHandlers.get(topic.getTopicId()).startWorker(topicSubsriber)).start();
                break;
            }
        }
    }

}
