package game.objects;

import game.Constants;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

public class Paddle {
double y;
Rectangle rect;

    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    public Paddle(int x_0,int y_0,Scene scene) {
        rect=new Rectangle(x_0,y_0,Constants.paddle_width,Constants.paddle_height);
        rect.setFill(Color.WHITE);
       Group root=(Group)scene.getRoot();
        root.getChildren().add(rect);
    }
    public void MoveDown(){
        if (rect.getBoundsInParent().getMaxY() < Constants.scene_height ) {
            rect.setTranslateY(rect.getTranslateY() + Constants.paddle_speed);
            y = rect.getBoundsInParent().getMinY();
        }
    }
    public void MoveUp(){
        if (rect.getBoundsInParent().getMaxY() > Constants.paddle_height ) {
            rect.setTranslateY(rect.getTranslateY() - Constants.paddle_speed);
            y = rect.getBoundsInParent().getMinY();
        }
    }

public double GetMaxY(){
        return rect.getBoundsInParent().getMaxY();
}
    public double GetMinY(){
        return rect.getBoundsInParent().getMinY();
    }
    public double GetMaxX(){
        return rect.getBoundsInParent().getMaxX();
    }
    public double GetMinX(){
        return rect.getBoundsInParent().getMinX();
    }

    public double getX() {
        return rect.getTranslateX();
    }

    public double getY() {
        return y;
    }







    /*void Move(){


        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.DOWN) {

                                    GraphicsContext gc = canvas.getGraphicsContext2D();
                                    gc.clearRect(x0, y0, x1, y1);
                                    y0 += 8;
                                    y1 += 8;
                                    gc.setStroke(Color.WHITE);
                                    gc.strokeLine(x0, y0, x1, y1);





                }
            }

       });

    }*/
};
