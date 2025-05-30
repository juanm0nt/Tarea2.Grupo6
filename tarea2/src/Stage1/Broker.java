package Stage1;

import java.util.HashMap;
import java.util.Map;

public class Broker {
    private Map<String, Topic> topics;

    public Broker() {
        topics = new HashMap<>();
    }

    public void publish(String topicName, String message) {
        Topic topic = topics.get(topicName);
        if (topic != null) {
            topic.broadcast(message);
        }
    }

    public void subscribe(String topicName, Subscriber subscriber) {
        Topic topic = topics.computeIfAbsent(topicName, k -> new Topic(topicName));
        topic.addSubscriber(subscriber);
    }
}
