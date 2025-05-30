package Stage2;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String name;
    private List<Subscriber> subscribers;

    public Topic(String name){
        this.name = name;
        subscribers = new ArrayList<>();
    }

    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    public void broadcast(String message){
        for (Subscriber subscriber : subscribers) {
            subscriber.update(message);
        }
    }
}
