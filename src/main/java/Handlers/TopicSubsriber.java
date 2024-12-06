package Handlers;

import Components.Subsriber;
import lombok.Data;
import lombok.NonNull;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class TopicSubsriber {
    private AtomicInteger offset;
    private Subsriber subsriber;

    public TopicSubsriber(@NonNull Subsriber subsriber) {
        this.subsriber = subsriber;
        this.offset = new AtomicInteger(0);
    }
}
