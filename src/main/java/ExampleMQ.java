import Components.Message;
import Components.Topic;
import Service.MQ;
import Service.Subscriber;
import lombok.SneakyThrows;

import java.util.Set;

public class ExampleMQ {

    @SneakyThrows
    public static void main(String[] args) {
        MQ mq = new MQ();

        final Topic topic1 = mq.createTopic("t1");
        final Topic topic2 = mq.createTopic("t2");

        // s1,s2 -> t1
        // s3 -> t2

        Subscriber sub1 = new Subscriber("s1", 200);
        Subscriber sub2 = new Subscriber("s2", 400);
        Subscriber sub3 = new Subscriber("s3", 200);

        mq.subscribe(topic1, sub1);

        mq.subscribe(topic2, sub3);

        mq.publishMessage(topic1, Message.builder().msg("m1").build());
        mq.publishMessage(topic2, Message.builder().msg("m2").build());
        mq.publishMessage(topic1, Message.builder().msg("m3").build());

        Thread.sleep(1500);
        mq.publishMessage(topic2, new Message("m4"));
        mq.publishMessage(topic1, new Message("m5"));

//        mq.resetOffset(topic1, sub2, 1);
        Thread.sleep(5000);
        mq.subscribe(topic1, sub2);

    }
}
