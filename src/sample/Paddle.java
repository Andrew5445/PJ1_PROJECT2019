package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


import java.util.HashMap;

public class Paddle {
double y;
Rectangle rect;

    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    public Paddle(int x_0,int y_0,Scene scene) {
        rect=new Rectangle(x_0,y_0,5,40);
        rect.setFill(Color.WHITE);
       Group root=(Group)scene.getRoot();
        root.getChildren().add(rect);
    }
    public void MoveDown(){
      rect.setTranslateY(rect.getTranslateY()+6);
      y=rect.getBoundsInParent().getMaxY();
       // y=rect.getY();
       //System.out.println(rect.getTranslateY());
    }
    public void MoveUp(){
        rect.setTranslateY(rect.getTranslateY()-6);
        y=rect.getBoundsInParent().getMaxY();

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
 public void setY(double value){
     rect.setTranslateY(value);

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
