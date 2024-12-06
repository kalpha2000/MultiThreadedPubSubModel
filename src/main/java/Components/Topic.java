package Components;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String topicName;
    private String topicId;
    private List<Subsriber> topicSubscribers;
    private List<Message> messages;

    public Topic(@NonNull String topicName, @NonNull String topicId) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.topicSubscribers = new ArrayList<>();
        this.messages = new ArrayList<>();
    }


    public void publishMessages(@NonNull Message message) {
        messages.add(message);
        sendToAllSubsribers();
    }

    public void addSubsriber(@NonNull Subsriber subsriber) {
        topicSubscribers.add(subsriber);
    }

    private void sendToAllSubsribers() {

    }
}
