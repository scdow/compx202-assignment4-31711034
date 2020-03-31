import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineJoin;

class EditableTriangle extends Group {

    private Polygon triangle;
    private Anchor[] anchors;

    public EditableTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        // Create a Polygon with three sides (Triangle)
        triangle = new Polygon();
        triangle.setStroke(Color.FORESTGREEN);
        triangle.setStrokeWidth(12);
        triangle.setStrokeLineJoin(StrokeLineJoin.ROUND);
        triangle.setFill(Color.CORNSILK);

        // Get the ObservableList of points (x1, y1, x2, y2, ...)
        ObservableList<Double> points = triangle.getPoints();
        points.setAll(x1, y1, x2, y2, x3, y3);

        getChildren().add(triangle);

        // Create anchors (handles) for the three corners of the triangle
        anchors = new Anchor[3];
        for (int i = 0; i < 3; i++) {
            final int ifinal = i;
            double x = points.get(2 * i);
            double y = points.get(2 * i + 1);
            anchors[i] = new Anchor(x, y);
            // When the anchor changes, update the Polygon's corresponding point
            anchors[i].addListener(obs -> {
                Anchor an = (Anchor) obs;
                triangle.getPoints().set(2 * ifinal, an.getCenterX());
                triangle.getPoints().set(2 * ifinal + 1, an.getCenterY());
            });
            getChildren().add(anchors[i]);
        }
    }
}
