package game.objects;

import game.Constants;

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

public class Ball {
    Scene scene;
    Circle ball;
    Paddle paddle1, paddle2;
    Score score;
    static double dx = Constants.ball_speed;
    static double dy = Constants.ball_speed;

    public Ball(Scene scene, Paddle paddle1, Paddle paddle2, Score score) {
        this.scene = scene;
        this.score = score;
        ball = new Circle(500, 250, 10);
        ball.setStroke(Color.INDIANRED);
        ball.setFill(Color.INDIANRED);
        this.paddle1 = paddle1;
        this.paddle2 = paddle2;
        Group root = (Group) this.scene.getRoot();
        root.getChildren().add(ball);

    }

    public void Movement() throws IOException {
        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(.050),
                new EventHandler<ActionEvent>() {


                    public void handle(ActionEvent event) {
                        if (ball.getBoundsInParent().getMinX() < 0) {
                            score.Add_point_to_player1();
                            ball.setTranslateX(0);
                            ball.setTranslateY(0);
                        }
                        if (ball.getBoundsInParent().getMaxX() > scene.getWidth()) {
                            score.Add_point_to_player2();
                            ball.setTranslateX(0);
                            ball.setTranslateY(0);
                        }

                        if (ball.getBoundsInParent().getMinY() < 0 || ball.getBoundsInParent().getMaxY() > scene.getHeight()) {
                            dy = dy * -1;
                        }

                        /*Handle ball movement on paddles*/

                        if ((ball.getBoundsInParent().getMinX() <= paddle1.GetMaxX()) && ((ball.getBoundsInParent().getMinY() >= paddle1.GetMinY() - 10) && (ball.getBoundsInParent().getMaxY() <= paddle1.GetMaxY() + 10))) {
                            dx *= -1;
          /*  System.out.println("Paddle minY: " + paddle1.GetMinY());
            System.out.println("Paddle maxY: " + paddle1.GetMaxY());
            System.out.println("Paddle minX: " + paddle1.GetMinX());
            System.out.println("Paddle maxX: " + paddle1.GetMaxX());
            System.out.println("Ball center X: " + ball.getBoundsInParent().getMinX() + "Y: " + ball.getBoundsInParent().getMinY());
            System.out.println("*************************************************");*/
                        }
                        if ((ball.getBoundsInParent().getMaxX() >= paddle2.GetMinX()) && ((ball.getBoundsInParent().getMinY() >= paddle2.GetMinY() - 10) && (ball.getBoundsInParent().getMaxY() <= paddle2.GetMaxY() + 10))) {
                            dx *= -1;
                           /* System.out.println("Paddle minY: "+paddle2.GetMinY());
                            System.out.println("Paddle maxY: "+paddle2.GetMaxY());
                            System.out.println("Paddle minX: "+paddle2.GetMinX());
                            System.out.println("Paddle maxX: "+paddle2.GetMaxX());
                            System.out.println("Ball center X: " + ball.getBoundsInParent().getMaxX() + "Y: " + ball.getBoundsInParent().getMinY());
                            System.out.println("*************************************************");*/
                        }
       /* System.out.print(ball.getTranslateX());
        System.out.println();*/
                        ball.setTranslateX(ball.getTranslateX() - dx);
                        ball.setTranslateY(ball.getTranslateY() + dy);
                    }
                });

        tl.getKeyFrames().add(moveBall);
        tl.play();
    }

    public double Y() {
        return ball.getTranslateY();
    }

    public double X() {
        return ball.getTranslateX();
    }

}
