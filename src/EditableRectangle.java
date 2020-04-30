import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;
import java.util.List;

class EditableRectangle extends Group implements MyObservable{

    private List<MyListener> listeners = new ArrayList<>();

    private Rectangle rectangle;

    private Anchor anchor_topleft;
    private Anchor anchor_topright;
    private Anchor anchor_bottomleft;
    private Anchor anchor_bottomright;


    private Anchor[] anchors;

    public EditableRectangle(double x,double y,double width,double height){
        rectangle = new Rectangle(x, y, width,height);
        rectangle.setFill(Color.LIGHTBLUE);
        rectangle.setStroke(Color.FORESTGREEN);
        rectangle.setStrokeWidth(10);
        rectangle.setStrokeType(StrokeType.CENTERED);
        getChildren().add(rectangle);

        //arbitrary anchors
        double[] points = new double[4];
        points[0] = x;
        points[1]=x+width;
        points[2] = y;
        points[3] = y+height;

        anchors = new Anchor[4];
        anchors[0] = new Anchor(points[0],points[2]);//top left
        anchors[1] = new Anchor(points[1],points[2]);//top right
        anchors[2] = new Anchor(points[1],points[3]);//bottom right
        anchors[3] = new Anchor(points[0],points[3]);//bottom left

        for(int i=0;i<4;i++){
            int ifinal = i;
            //when anchors changing, update corresponding points
            anchors[ifinal].addListener(observable -> {
                if(ifinal==0||ifinal==3){
                    points[0]=anchors[ifinal].getCenterX();
                }
                if(ifinal==1||ifinal==2){
                    points[1]=anchors[ifinal].getCenterX();
                }
                if(ifinal==0||ifinal==1){
                    points[2]=anchors[ifinal].getCenterY();
                }
                if(ifinal==2||ifinal==3){
                    points[3]=anchors[ifinal].getCenterY();
                }

                //set rectangle
                rectangle.setX(points[0]);
                rectangle.setY(points[2]);
                rectangle.setWidth(Math.abs(points[1]-points[0]));
                rectangle.setHeight(Math.abs(points[3]-points[2]));
                if(points[0]>points[1]){
                    rectangle.setX(points[1]);
//                    rectangle.setWidth(points[0]-points[1]);
                }
                if(points[2]>points[3]){
                    rectangle.setY(points[3]);
//                    rectangle.setHeight(points[2]-points[3]);
                }

                //set anchors
                anchors[0].setCenterX(points[0]);
                anchors[0].setCenterY(points[2]);
                anchors[1].setCenterX(points[1]);
                anchors[1].setCenterY(points[2]);
                anchors[2].setCenterX(points[1]);
                anchors[2].setCenterY(points[3]);
                anchors[3].setCenterX(points[0]);
                anchors[3].setCenterY(points[3]);

                notifyListeners();
            });

            getChildren().add(anchors[i]);
        }

        //specific anchors
        /*anchor_topleft = new Anchor(rectangle.getX(),rectangle.getY());
        anchor_topleft.addListener(obs ->{ //obs: myObserverable
            Anchor an = (Anchor) obs;
            //to just move top left corner and resize
//            rectangle.setWidth(rectangle.getWidth()+(rectangle.getX()-anchor_topleft.getCenterX()));
//            rectangle.setHeight(rectangle.getHeight()+(rectangle.getY()-anchor_topleft.getCenterY()));
            rectangle.setWidth(anchor_bottomright.getCenterX()-anchor_topleft.getCenterX());
            rectangle.setHeight(anchor_bottomright.getCenterY()-anchor_topleft.getCenterY());
            //move the corner
            rectangle.setX(anchor_topleft.getCenterX());
            rectangle.setY(anchor_topleft.getCenterY());
            //change anchors
            anchor_topright.setCenterY(rectangle.getY());
            anchor_bottomleft.setCenterX(rectangle.getX());
            //update
            notifyListeners();
        });
        getChildren().add(anchor_topleft);

        anchor_bottomright = new Anchor(rectangle.getX()+rectangle.getWidth(),rectangle.getY()+rectangle.getHeight());
        anchor_bottomright.addListener(obs ->{ //obs: myObserverable
            Anchor an = (Anchor) obs;
//            rectangle.setWidth(anchor_bottomright.getCenterX()-rectangle.getX());
//            rectangle.setHeight(anchor_bottomright.getCenterY()-rectangle.getY());
            rectangle.setWidth(anchor_bottomright.getCenterX()-anchor_topleft.getCenterX());
            rectangle.setHeight(anchor_bottomright.getCenterY()-anchor_topleft.getCenterY());
            //change anchors
            anchor_topright.setCenterX(rectangle.getX()+rectangle.getWidth());
            anchor_bottomleft.setCenterY(rectangle.getY()+rectangle.getHeight());
            //update
            notifyListeners();
        });
        getChildren().add(anchor_bottomright);

        anchor_topright = new Anchor(rectangle.getX()+rectangle.getWidth(),rectangle.getY());
        anchor_topright.addListener(obs ->{
            rectangle.setWidth(anchor_topright.getCenterX()-anchor_bottomleft.getCenterX());
            rectangle.setHeight(anchor_bottomleft.getCenterY()-anchor_topright.getCenterY());

            rectangle.setY(anchor_bottomleft.getCenterY()-rectangle.getHeight());
            //change anchors
            anchor_topleft.setCenterY(rectangle.getY());
            anchor_bottomright.setCenterX(rectangle.getX()+rectangle.getWidth());
            //update
            notifyListeners();
        });
        getChildren().add(anchor_topright);

        anchor_bottomleft = new Anchor(rectangle.getX(), rectangle.getY()+rectangle.getHeight());
        anchor_bottomleft.addListener(obs ->{
            rectangle.setWidth(anchor_topright.getCenterX()-anchor_bottomleft.getCenterX());
            rectangle.setHeight(anchor_bottomleft.getCenterY()-anchor_topright.getCenterY());

            rectangle.setX(anchor_bottomright.getCenterX()-rectangle.getWidth());
            //change anchors
            anchor_topleft.setCenterX(rectangle.getX());
            anchor_bottomright.setCenterY(rectangle.getY()+rectangle.getHeight());
            //update
            notifyListeners();
        });
        getChildren().add(anchor_bottomleft);*/


//        anchors = new Anchor[4];
//        for (int i = 0; i<4; i++){
//            final int ifinal =i;
////            anchors[i] = new Anchor(rectangle.getX(),rectangle.getY());
//        }
    }

    double getWidth(){
        return rectangle.getWidth();
    }

    double getHeight(){
        return rectangle.getHeight();
    }

    @Override
    public void addListener(MyListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(MyListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (MyListener listener : listeners) {
            listener.update(this);
        }
    }
}
