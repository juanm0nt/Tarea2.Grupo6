package Stage2;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class VideoPublisher extends Publisher {
    private HBox view;
    private TextField urlField;

    public VideoPublisher(String name, Broker broker, String topicName){
        super(name, broker, topicName);

        view = new HBox();
        urlField = new TextField();
        urlField.setPromptText("Ingrese URL del video y presione ENTER");

        urlField.setOnAction(e -> {
            String url = urlField.getText();
            if (!url.isEmpty()) {
                publish(url);
                urlField.clear();
            }
        });

        view.getChildren().add(urlField);
    }

    public HBox getView(){
        return view;
    }
}
