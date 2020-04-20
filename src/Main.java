import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

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

        EditableRectangle rectangle = new EditableRectangle(500,200,130,100);
        root.getChildren().add(rectangle);

        String content = String.valueOf(rectangle.getWidth()*rectangle.getHeight());
        Text text = new Text(700,100, "Area: "+content);
        text.setFill(Color.BLACK);
        text.setFont(Font.font(20));
        root.getChildren().add(text);

        String content_perimeter = String.valueOf(2*(rectangle.getHeight()+rectangle.getWidth()));
        Text text2 = new Text(400,100, "Perimeter: "+content_perimeter);
        text2.setFont(Font.font(20));
        root.getChildren().add(text2);

        rectangle.addListener(c -> {
//            System.out.println("Rectangle transforming");
            String area = String.valueOf(rectangle.getWidth()*rectangle.getHeight());
            text.setText("Area: "+area);

            String perimeter = String.valueOf(2*(rectangle.getHeight()+rectangle.getWidth()));
            text2.setText("Perimeter: "+perimeter);
        });



        Scene scene = new Scene(root, 1000, 700, Color.BISQUE);
        stage.setTitle("Assignment 4");
        stage.setScene(scene);

        stage.show();
    }
}


