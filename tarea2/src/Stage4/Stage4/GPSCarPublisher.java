package Stage4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * La clase que representa un publicador de posiciones GPS de un vehículo.
 * Publica posiciones en el tiempo, basadas en un archivo de rutas.
 */
public class GPSCarPublisher extends Publisher {
    private HBox view;
    private Label label;

    private List<Position> positions;
    private int currentTime;
    private int totalTime;
    private Timeline timeline;

    /**
     * Constructor del publicador GPS.
     * @param name nombre del publicador.
     * @param broker instancia del broker que maneja los tópicos.
     * @param topicName nombre del topico donde se publican las posiciones GPS.
     */
    public GPSCarPublisher(String name, Broker broker, String topicName) {
        super(name, broker, topicName);

        view = new HBox(10);
        label = new Label(name + " -> " + topicName + ": Cargando...");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione archivo de posiciones");
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                loadPositions(file);
                startPublishing();
                label.setText(name + " -> " + topicName + ": Enviando posiciones...");
            } catch (FileNotFoundException e) {
                label.setText("Error: archivo no encontrado.");
            }
        } else {
            label.setText("No se seleccionó archivo.");
        }

        view.getChildren().add(label);
    }

    /**
     * Devuelve la vista gráfica del publicador.
     * @return contenedor HBox con la vista.
     */
    public HBox getView() {
        return view;
    }

    /**
     * Carga las posiciones desde un archivo.
     * @param file archivo de texto con las posiciones.
     * @throws FileNotFoundException si el archivo no existe.
     */
    private void loadPositions(File file) throws FileNotFoundException {
        positions = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split("\\s+");
            int time = Integer.parseInt(parts[0]);
            double x = Double.parseDouble(parts[1]);
            double y = Double.parseDouble(parts[2]);
            positions.add(new Position(time, x, y));
        }
        scanner.close();
        if (!positions.isEmpty()) {
            totalTime = positions.get(positions.size() - 1).time;
        }
    }

    /**
     * Inicia la publicación de posiciones en el tiempo.
     */
    private void startPublishing() {
        if (positions.isEmpty()) return;

        currentTime = 0;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (currentTime <= totalTime) {
                double[] interpolatedPos = getInterpolatedPosition(currentTime);
                publish(currentTime + " " + interpolatedPos[0] + " " + interpolatedPos[1]);
                currentTime++;
            } else {
                timeline.stop();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Calcula la posición en un tiempo dado.
     * @param time tiempo actual.
     * @return arreglo con las coordenadas {x, y}.
     */
    private double[] getInterpolatedPosition(int time) {
        for (int i = 0; i < positions.size() - 1; i++) {
            Position start = positions.get(i);
            Position end = positions.get(i + 1);
            if (time >= start.time && time <= end.time) {
                double factor = (double) (time - start.time) / (end.time - start.time);
                double x = start.x + factor * (end.x - start.x);
                double y = start.y + factor * (end.y - start.y);
                return new double[]{x, y};
            }
        }
        Position last = positions.get(positions.size() - 1);
        return new double[]{last.x, last.y};
    }

    /**
     * Clase interna que representa una posición GPS.
     */
    private static class Position {
        int time;
        double x, y;

        Position(int time, double x, double y) {
            this.time = time;
            this.x = x;
            this.y = y;
        }
    }
}
