import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;
import java.util.List;

public class Anchor extends Circle implements MyObservable {

    private List<MyListener> listeners = new ArrayList<>();

    public Anchor(double x, double y) {
        super(x, y, 10);
        setFill(Color.RED.deriveColor(1, 1, 1, 0.5));
        setStroke(Color.RED);
        setStrokeWidth(2);
        setStrokeType(StrokeType.OUTSIDE);

        setOnMouseDragged(mev -> {
            setCenterX(mev.getX());
            setCenterY(mev.getY());
            notifyListeners();
        });
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