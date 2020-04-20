import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

class EditableRectangle extends Group {

    private Rectangle rectangle;
//    private Anchor[] anchors;
    private Anchor anchor_topleft;
    private Anchor anchor_bottomright;

    public EditableRectangle(double x,double y,double width,double height){
        rectangle = new Rectangle(x, y, width,height);
        rectangle.setFill(Color.LIGHTBLUE);
        rectangle.setStroke(Color.FORESTGREEN);
        rectangle.setStrokeWidth(10);
        rectangle.setStrokeType(StrokeType.CENTERED);
        getChildren().add(rectangle);

        anchor_topleft = new Anchor(rectangle.getX(),rectangle.getY());
        anchor_topleft.addListener(obs ->{ //obs: myObserverable
            Anchor an = (Anchor) obs;
            rectangle.setX(anchor_topleft.getCenterX());
            rectangle.setY(anchor_topleft.getCenterY());
        });
        getChildren().add(anchor_topleft);

        anchor_bottomright = new Anchor(rectangle.getX()+rectangle.getWidth(),rectangle.getY()+rectangle.getHeight());
        anchor_bottomright.addListener(obs ->{ //obs: myObserverable
            Anchor an = (Anchor) obs;
            rectangle.setWidth(anchor_bottomright.getCenterX()-rectangle.getX());
            rectangle.setHeight(anchor_bottomright.getCenterY()-rectangle.getY());
        });
        getChildren().add(anchor_bottomright);

//        anchors = new Anchor[3];
//        for (int i = 0; i < 3; i++) {
//            final int ifinal = i;
//            double x = points.get(2 * i);
//            double y = points.get(2 * i + 1);
//            anchors[i] = new Anchor(x, y);
//            // When the anchor changes, update the Polygon's corresponding point
//            anchors[i].addListener(obs -> {
//                Anchor an = (Anchor) obs;
//                triangle.getPoints().set(2 * ifinal, an.getCenterX());
//                triangle.getPoints().set(2 * ifinal + 1, an.getCenterY());
//            });
//            getChildren().add(anchors[i]);
//        }

    }

}
