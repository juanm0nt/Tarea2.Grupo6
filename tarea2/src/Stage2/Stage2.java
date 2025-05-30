package Stage2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class Stage2 extends Application {
    private Broker broker;
    private VBox publishersPane;
    private VBox followersPane;

    @Override
    public void start(Stage primaryStage){
        broker = new Broker();

        VBox root = new VBox();
        MenuBar menuBar = new MenuBar();
        root.getChildren().add(menuBar);

        Menu publisherMenu = new Menu("Publisher");
        MenuItem videoPublisherItem = new MenuItem("Video");
        publisherMenu.getItems().add(videoPublisherItem);

        Menu subscriberMenu = new Menu("Subscriber");
        MenuItem videoFollowerItem = new MenuItem("Video");
        subscriberMenu.getItems().add(videoFollowerItem);

        menuBar.getMenus().addAll(publisherMenu, subscriberMenu);

        publishersPane = new VBox(10);
        followersPane = new VBox(10);

        root.getChildren().addAll(publishersPane, followersPane);

        videoPublisherItem.setOnAction(e -> createVideoPublisher());
        videoFollowerItem.setOnAction(e -> createVideoFollower());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Simulador Patrón Publicador-Suscriptor - Etapa 2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createVideoPublisher(){
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setHeaderText("Ingrese el nombre del publicador:");
        Optional<String> nameOpt = nameDialog.showAndWait();

        if (!nameOpt.isPresent()) return;

        TextInputDialog topicDialog = new TextInputDialog();
        topicDialog.setHeaderText("Ingrese el nombre del tópico:");
        Optional<String> topicOpt = topicDialog.showAndWait();

        if (!topicOpt.isPresent()) return;

        VideoPublisher vp = new VideoPublisher(nameOpt.get(), broker, topicOpt.get());
        publishersPane.getChildren().add(vp.getView());
    }

    private void createVideoFollower(){
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setHeaderText("Ingrese el nombre del suscriptor:");
        Optional<String> nameOpt = nameDialog.showAndWait();

        if (!nameOpt.isPresent()) return;

        TextInputDialog topicDialog = new TextInputDialog();
        topicDialog.setHeaderText("Ingrese el nombre del tópico:");
        Optional<String> topicOpt = topicDialog.showAndWait();

        if (!topicOpt.isPresent()) return;

        VideoFollower vf = new VideoFollower(nameOpt.get(), broker, topicOpt.get());
        followersPane.getChildren().add(vf.getView());
    }

    public static void main(String[] args){
        launch(args);
    }
}
