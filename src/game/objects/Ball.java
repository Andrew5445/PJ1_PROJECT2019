package game.objects;

import game.Constants;
import game.Multiplayer;
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
    //private final BlockingQueue<Integer> queue;
    Multiplayer mp;
    double xMin;
    double yMin;
    double xMax;
    double yMax;
    double centerX;
    double centerY;
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
        KeyFrame moveBall = new KeyFrame(Duration.seconds(.0050),
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {
                        centerX = ball.getBoundsInParent().getMaxY() - ball.getBoundsInParent().getMinY();
                        centerY = ball.getBoundsInParent().getMaxX() - ball.getBoundsInParent().getMinX();
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

                        if ((ball.getBoundsInParent().getMinX() <= paddle1.GetMaxX()) && ((ball.getBoundsInParent().getMinY() >= paddle1.GetMinY()) || (ball.getBoundsInParent().getMaxY() <= paddle1.GetMaxY()))) {
                            dx *= -1;
                        }
                        if ((ball.getBoundsInParent().getMaxX() >= paddle2.GetMinX()) && ((ball.getBoundsInParent().getMinY() >= paddle2.GetMinY()) || (ball.getBoundsInParent().getMaxY() <= paddle2.GetMaxY()))) {
                            dx *= -1;
                        }

                        ball.setTranslateX(ball.getTranslateX() + dx);
                        ball.setTranslateY(ball.getTranslateY() + dy);
                    }
                });

        tl.getKeyFrames().add(moveBall);
        tl.play();
    }

    public double Get_MinX() {
        return ball.getBoundsInParent().getMinX();
    }

    public double Get_MaxX() {
        return ball.getBoundsInParent().getMaxX();
    }

    public double Y(){
        return ball.getBoundsInParent().getMinY();
    }

    public double X(){
        return ball.getBoundsInParent().getMinX();
    }

}
