package pl.sda.dystans;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main extends Application {
    private DoubleProperty translate = new SimpleDoubleProperty();

    static List<Point> points = new LinkedList<>();

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            double x = (Math.random() * 199.9) - 100.0;
            double y = (Math.random() * 199.9) - 100.0;

            points.add(new Point(x, y));
            System.out.println("Dodano punkt nr " + (i + 1) + " o współrzędnych: (" + x + " ; " + y + ")");
        }

        List<Distance> distances = new LinkedList<>();

        for (int i = 0; i < 99; i++) {
            for (int j = i + 1; j < 100; j++) {
                double x1 = points.get(i).getX();
                double x2 = points.get(j).getX();
                double y1 = points.get(i).getY();
                double y2 = points.get(j).getY();
                double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
                distances.add(new Distance(i, j, distance));
                System.out.println("Dystans między punktem nr " + (i + 1) + " a punktem " + (j + 1) + " wynosi " + distance);

            }
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("Lista.json"), points);
            objectMapper.writeValue(new File("ListaOdległosci.json"), distances);
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello JavaFX");
        stage.setScene(makeScene());
        stage.show();
    }

    private Scene makeScene() {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.BLACK);
        root.getChildren().addAll(createRectangles());
        return scene;
    }

    private List<Rectangle> createRectangles() {
        List<Rectangle> rectangles = new ArrayList<>();
        Rectangle rect;
        int i = 0;
        for (; i < 100; ) {
            rect = new Rectangle((points.get(i).getX() *2)+ 250.0, (points.get(i).getY() *2)+ 250.0, 2, 2);
            rect.setStroke(Color.RED);
            rectangles.add(rect);
            i++;
        }
        return rectangles;
    }
}
