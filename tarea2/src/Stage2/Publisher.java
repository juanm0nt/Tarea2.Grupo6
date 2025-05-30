package Stage2;

public class Publisher extends Component {
    protected Broker broker;
    protected String topicName;

    public Publisher(String name, Broker broker, String topicName){
        super(name);
        this.broker = broker;
        this.topicName = topicName;
    }

    public void publish(String message){
        broker.publish(topicName, message);
    }
}
