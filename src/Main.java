import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

import java.awt.*;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(final Stage stage) {
        Group root = new Group();
        EditableTriangle triangle = new EditableTriangle(100.0, 100.0, 150.0, 50.0, 250.0, 150.0);
        EditableTriangle triangle2 = new EditableTriangle(200,400,150,200,300,300);
        root.getChildren().add(triangle);
        root.getChildren().add(triangle2);

        EditableRectangle rectangle = new EditableRectangle(400,200,130,100);
        root.getChildren().add(rectangle);

        Scene scene = new Scene(root, 600, 500, Color.BISQUE);
        stage.setTitle("Assignment 4");
        stage.setScene(scene);

        stage.show();
    }
}


