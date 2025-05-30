package Stage4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Optional;

public class Stage4 extends Application {
    private Broker broker;
    private VBox publishersPane;
    private VBox followersPane;

    @Override
    public void start(Stage primaryStage) {
        broker = new Broker();

        VBox root = new VBox();
        MenuBar menuBar = new MenuBar();
        root.getChildren().add(menuBar);

        Menu publisherMenu = new Menu("Publisher");
        MenuItem videoPublisherItem = new MenuItem("Video");
        MenuItem gpsPublisherItem = new MenuItem("Car's GPS");
        publisherMenu.getItems().addAll(videoPublisherItem, gpsPublisherItem);

        Menu subscriberMenu = new Menu("Subscriber");
        MenuItem videoFollowerItem = new MenuItem("Video");
        MenuItem gpsFollowerItem = new MenuItem("Car's GPS");
        subscriberMenu.getItems().addAll(videoFollowerItem, gpsFollowerItem);

        menuBar.getMenus().addAll(publisherMenu, subscriberMenu);

        publishersPane = new VBox(10);
        followersPane = new VBox(10);

        root.getChildren().addAll(publishersPane, followersPane);

        // Acción Publisher Video
        videoPublisherItem.setOnAction(e -> createVideoPublisher());
        // Acción Publisher GPS
        gpsPublisherItem.setOnAction(e -> createGPSPublisher());

        // Acción Subscriber Video
        videoFollowerItem.setOnAction(e -> createVideoFollower());
        // Acción Subscriber GPS
        gpsFollowerItem.setOnAction(e -> createGPSFollower());

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("Publisher-Subscriber Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createVideoPublisher() {
        Optional<String> nameOpt = askInput("Ingrese el nombre del publicador:");
        if (!nameOpt.isPresent()) return;
        Optional<String> topicOpt = askInput("Ingrese el nombre del tópico:");
        if (!topicOpt.isPresent()) return;

        VideoPublisher vp = new VideoPublisher(nameOpt.get(), broker, topicOpt.get());
        publishersPane.getChildren().add(vp.getView());
    }

    private void createGPSPublisher() {
        Optional<String> nameOpt = askInput("Ingrese el nombre del publicador:");
        if (!nameOpt.isPresent()) return;
        Optional<String> topicOpt = askInput("Ingrese el nombre del tópico:");
        if (!topicOpt.isPresent()) return;

        GPSCarPublisher gpsPublisher = new GPSCarPublisher(nameOpt.get(), broker, topicOpt.get());
        publishersPane.getChildren().add(gpsPublisher.getView());
    }

    private void createVideoFollower() {
        Optional<String> nameOpt = askInput("Ingrese el nombre del suscriptor:");
        if (!nameOpt.isPresent()) return;
        Optional<String> topicOpt = askInput("Ingrese el nombre del tópico:");
        if (!topicOpt.isPresent()) return;

        VideoFollower vf = new VideoFollower(nameOpt.get(), broker, topicOpt.get());
        followersPane.getChildren().add(vf.getView());
    }

    private void createGPSFollower() {
        Optional<String> nameOpt = askInput("Ingrese el nombre del suscriptor:");
        if (!nameOpt.isPresent()) return;
        Optional<String> topicOpt = askInput("Ingrese el nombre del tópico:");
        if (!topicOpt.isPresent()) return;

        GPSCarFollower gpsFollower = new GPSCarFollower(nameOpt.get(), broker, topicOpt.get());
        followersPane.getChildren().add(new Label(nameOpt.get() + " siguiendo " + topicOpt.get()));
    }

    private Optional<String> askInput(String headerText) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(headerText);
        return dialog.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
