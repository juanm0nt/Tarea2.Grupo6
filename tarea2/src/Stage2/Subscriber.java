package Stage2;

public abstract class Subscriber extends Component {
    protected Broker broker;
    protected String topicName;

    public Subscriber(String name, Broker broker, String topicName){
        super(name);
        this.broker = broker;
        this.topicName = topicName;
        broker.subscribe(topicName, this);
    }

    public abstract void update(String message);
}
