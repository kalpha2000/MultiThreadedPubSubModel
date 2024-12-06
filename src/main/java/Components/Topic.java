package Components;

import Handlers.TopicSubsriber;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class Topic {
    private String topicName;
    private String topicId;
    private List<TopicSubsriber> topicSubscribers;
    private List<Message> messages;

    public Topic(@NonNull String topicName, @NonNull String topicId) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.topicSubscribers = new ArrayList<>();
        this.messages = new ArrayList<>();
    }


    public void addMessages(@NonNull Message message) {
        messages.add(message);
    }

    public void addSubsriber(@NonNull TopicSubsriber subsriber) {
        topicSubscribers.add(subsriber);
    }
}
