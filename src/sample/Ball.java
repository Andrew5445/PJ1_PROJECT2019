package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

public class Ball {
    //private final BlockingQueue<Integer> queue;
    Multiplayer mp;
    double xMin;
    double yMin;
    Scene scene;
    Circle ball;
    Paddle paddle1,paddle2;
    Score score;
    static int dx = 2;
    static int dy = 2;
    public Ball(Scene scene,Paddle paddle1,Paddle paddle2,Score score){
        this.scene=scene;
        this.score=score;
        ball = new Circle(500, 250, 10);
        ball.setStroke(Color.WHITE);
        ball.setFill(Color.WHITE);
        this.paddle1=paddle1;
        this.paddle2=paddle2;
        Group root = (Group) this.scene.getRoot();
        root.getChildren().add(ball);

    }
    public void Movement() throws IOException {
        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);

        KeyFrame moveBall = new KeyFrame(Duration.seconds(.0050),
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {

                        xMin = ball.getBoundsInParent().getMinX();
                        yMin = ball.getBoundsInParent().getMinY();
                        double xMax = ball.getBoundsInParent().getMaxX();
                        double yMax = ball.getBoundsInParent().getMaxY();

                        if (xMin < 0) {
                            score.Add_point_to_player1();
                            ball.setTranslateX(0);
                            ball.setTranslateY(0);
                            xMin=ball.getBoundsInParent().getMinX();
                            yMin = ball.getBoundsInParent().getMinY();
                        }
                        if (xMax > scene.getWidth()) {
                            score.Add_point_to_player2();
                            ball.setTranslateX(0);
                            ball.setTranslateY(0);
                            xMin=ball.getBoundsInParent().getMinX();
                            yMin = ball.getBoundsInParent().getMinY();
                        }

                        if (yMin < 0 || yMax > scene.getHeight()) {
                            dy = dy * -1;
                        }

                        //if (xMax>=paddle1.GetX0()+5)&&(yMin>paddle1.GetY0()&&yMax<50){dx=dx*-1;}
                        if ((xMin<=paddle1.GetMaxX())&&((yMin>=paddle1.GetMinY())&&(yMax<=paddle1.GetMaxY()))){dx*=-1;}
                        if ((xMax>=paddle2.GetMinX())&&((yMin>=paddle2.GetMinY())&&(yMax<=paddle2.GetMaxY()))){dx*=-1;}
                        //dolni okraj paddle1
                        if ((xMin<=paddle1.GetMaxX())&&(yMin<paddle1.GetMaxY()&& yMin>paddle1.GetMinY())){dx*=-1;}
                        //horni okraj paddle1
                        if ((xMin<=paddle1.GetMaxX())&&(yMax>paddle1.GetMinY()&& yMax<paddle1.GetMaxY())){dx*=-1;}
                        //dolni okraj paddle2
                        if ((xMin>=paddle2.GetMinX())&&(yMin<paddle2.GetMaxY()&& yMin>paddle2.GetMinY())){dx*=-1;}
                        //horni okraj paddle2
                        if ((xMin>=paddle2.GetMinX())&&(yMax>paddle2.GetMinY()&& yMax<paddle2.GetMaxY())){dx*=-1;}
                        ball.setTranslateX(ball.getTranslateX() + dx);
                        ball.setTranslateY(ball.getTranslateY() + dy);
                        xMin=ball.getBoundsInParent().getMinX();
                        yMin = ball.getBoundsInParent().getMinY();
                    }
                });

        tl.getKeyFrames().add(moveBall);
        tl.play();
    }
    public double Get_MinX(){
        return ball.getBoundsInParent().getMinX();
    }
    public double Get_MaxX(){
        return ball.getBoundsInParent().getMaxX();
    }
    public double X(){
        return xMin;

    }
    public double Y(){
        return yMin;
    }

}
