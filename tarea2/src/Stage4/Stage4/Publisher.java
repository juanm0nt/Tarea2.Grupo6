package Stage4;

/**
 * Clase que representa un publicador dentro del sistema Publisher-Subscriber.
 * Un publicador envía mensajes asociados a un tópico.
 */
public class Publisher extends Component {
    protected Broker broker;
    protected String topicName;

    /**
     * Constructor del publicador.
     * @param name nombre del publicador.
     * @param broker instancia del broker que maneja los tópicos.
     * @param topicName nombre del tópico al cual publicará mensajes.
     */
    public Publisher(String name, Broker broker, String topicName) {
        super(name);
        this.broker = broker;
        this.topicName = topicName;
    }

    /**
     * Publica un mensaje en el tópico asociado.
     * @param message mensaje a publicar.
     */
    public void publish(String message) {
        broker.publish(topicName, message);
    }
}
