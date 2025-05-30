package Stage1;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class VideoFollower extends Subscriber {
    private HBox view;
    private Button videoButton;

    public VideoFollower(String name, Broker broker, String topicName) {
        super(name, broker, topicName);

        view = new HBox();
        videoButton = new Button("Esperando video...");
        view.getChildren().add(videoButton);

        // En esta etapa el botón solo muestra el nombre del último video publicado
        // (No reacciona aun al hacer click, eso es para la etapa 2)
    }

    @Override
    public void update(String message){
        videoButton.setText(message);
    }

    public HBox getView(){
        return view;
    }
}
