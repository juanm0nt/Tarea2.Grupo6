package Stage4;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GPSCarFollower extends Subscriber {
    private Stage stage;
    private Pane pane;
    private Circle car;
    private Label positionLabel;

    public GPSCarFollower(String name, Broker broker, String topicName) {
        super(name, broker, topicName);

        stage = new Stage();
        pane = new Pane();
        car = new Circle(10, Color.RED);
        positionLabel = new Label("Posición: (0, 0)");

        pane.getChildren().addAll(car, positionLabel);

        Scene scene = new Scene(pane, 600, 600);
        stage.setTitle("Car Tracker:" + name + ", Topic:" + topicName);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void update(String message) {
        String[] parts = message.split("\\s+");
        double t = Double.parseDouble(parts[0]);
        double x = Double.parseDouble(parts[1]);
        double y = Double.parseDouble(parts[2]);

        car.setCenterX(x);
        car.setCenterY(y);
        positionLabel.setText(String.format("t %.1f, x %.1f, y %.1f", t, x, y));
        positionLabel.setLayoutX(10);
        positionLabel.setLayoutY(580);
    }
}
