package Stage3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label; // 👈 ESTE ERA EL IMPORT QUE FALTABA

import java.util.Optional;

public class Stage3 extends Application {
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
        MenuItem gpsPublisherItem = new MenuItem("Car's GPS");
        publisherMenu.getItems().add(gpsPublisherItem);

        Menu subscriberMenu = new Menu("Subscriber");
        MenuItem gpsFollowerItem = new MenuItem("Car Follower");
        subscriberMenu.getItems().add(gpsFollowerItem);

        menuBar.getMenus().addAll(publisherMenu, subscriberMenu);

        publishersPane = new VBox(10);
        followersPane = new VBox(10);

        root.getChildren().addAll(publishersPane, followersPane);

        gpsPublisherItem.setOnAction(e -> createGPSPublisher());
        gpsFollowerItem.setOnAction(e -> createGPSFollower());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Simulador Patrón Publicador-Suscriptor - Etapa 3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createGPSPublisher() {
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setHeaderText("Ingrese el nombre del publicador:");
        Optional<String> nameOpt = nameDialog.showAndWait();

        if (!nameOpt.isPresent()) return;

        TextInputDialog topicDialog = new TextInputDialog();
        topicDialog.setHeaderText("Ingrese el nombre del tópico:");
        Optional<String> topicOpt = topicDialog.showAndWait();

        if (!topicOpt.isPresent()) return;

        GPSCarPublisher gpsPublisher = new GPSCarPublisher(nameOpt.get(), broker, topicOpt.get());
        publishersPane.getChildren().add(gpsPublisher.getView());
    }

    private void createGPSFollower() {
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setHeaderText("Ingrese el nombre del suscriptor:");
        Optional<String> nameOpt = nameDialog.showAndWait();

        if (!nameOpt.isPresent()) return;

        TextInputDialog topicDialog = new TextInputDialog();
        topicDialog.setHeaderText("Ingrese el nombre del tópico:");
        Optional<String> topicOpt = topicDialog.showAndWait();

        if (!topicOpt.isPresent()) return;

        GPSCarFollower gpsFollower = new GPSCarFollower(nameOpt.get(), broker, topicOpt.get());
        followersPane.getChildren().add(new Label(nameOpt.get() + " siguiendo " + topicOpt.get()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
