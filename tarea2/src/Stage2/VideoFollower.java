package Stage2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class VideoFollower extends Subscriber {
    private HBox view;
    private Button videoButton;
    private String currentVideoUrl;

    public VideoFollower(String name, Broker broker, String topicName){
        super(name, broker, topicName);

        view = new HBox(10);
        videoButton = new Button(topicName + " -> " + name + ": Nothing yet");
        view.getChildren().add(videoButton);

        videoButton.setOnAction(e -> {
            if (currentVideoUrl != null && !currentVideoUrl.isEmpty()){
                openVideoPlayer(currentVideoUrl);
            }
        });
    }

    @Override
    public void update(String message){
        currentVideoUrl = message;
        videoButton.setText(topicName + " -> " + name + ": " + message);
    }

    public HBox getView(){
        return view;
    }

    private void openVideoPlayer(String url){
        Stage videoStage = new Stage();
        Media media = new Media(url);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        // Controles
        Button playButton = new Button("▶");   // Play
        playButton.setOnAction(e -> mediaPlayer.play());

        Button pauseButton = new Button("⏸"); // Pause
        pauseButton.setOnAction(e -> mediaPlayer.pause());

        Button stopButton = new Button("⏹");  // Stop
        stopButton.setOnAction(e -> mediaPlayer.stop());

        Slider volumeSlider = new Slider(0, 1, 0.5); // Rango delvolumen 0 a 1
        mediaPlayer.volumeProperty().bind(volumeSlider.valueProperty());

        HBox controls = new HBox(10, playButton, pauseButton, stopButton, new javafx.scene.control.Label("Volume"), volumeSlider);
        controls.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(mediaView);
        root.setBottom(controls);

        Scene scene = new Scene(root, 800, 600);

        videoStage.setTitle("Reproductor de Video");
        videoStage.setScene(scene);
        videoStage.show();

        mediaPlayer.play(); // esto es para reproducir automáticamente al abrir
    }
}
