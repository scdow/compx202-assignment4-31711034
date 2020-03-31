import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(final Stage stage) {
        Group root = new Group();
        EditableTriangle triangle = new EditableTriangle(100.0, 100.0, 150.0, 50.0, 250.0, 150.0);
        root.getChildren().add(triangle);

        Scene scene = new Scene(root, 600, 500, Color.BISQUE);
        stage.setTitle("Assignment 4");
        stage.setScene(scene);

        stage.show();
    }
}


