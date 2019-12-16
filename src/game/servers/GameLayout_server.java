package game.servers;

import game.Constants;
import game.objects.Ball;
import game.objects.Paddle;
import game.objects.Score;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class GameLayout_server {

    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    Scene scene2_;
    Scene scene_;
    Paddle paddle1;
    Paddle paddle2_;
    Score score_;
    Ball ball;

    static double dx = Constants.ball_speed;
    static double dy = Constants.ball_speed;
    public GameLayout_server(Stage stage,boolean Multiplayer_Mode) throws IOException, ExecutionException, InterruptedException {
        stage.setResizable(false);
        Button button1=new Button("Click to start game");
        button1.setOnAction(e-> stage.setScene(scene2_));
        button1.setStyle("-fx-border-color: white;" +
                "-fx-background-color: black;" +
                "-fx-font-size: 50px;"+"-fx-text-fill:white");

        HBox layout1=new HBox();
        layout1.setAlignment(Pos.CENTER);
        layout1.setStyle("-fx-background-color: black;");
        layout1.getChildren().addAll(button1);
        button1.setPrefWidth(1001);
        button1.setPrefHeight(501);
        scene_=new Scene(layout1,Constants.scene_width, Constants.scene_height);
        Group layout2=new Group();
        scene2_=new Scene(layout2,Constants.scene_width, Constants.scene_height);
        scene2_.setFill(Color.BLACK);
        //paddle1=new Paddle(60,50,scene2);
        paddle2_=new Paddle(940,50,scene2_);
        score_=new Score(scene2_);
        //ball=new Ball(scene2,paddle1,paddle2,score);
       // Ball_Movement();
        Paddle_movement();
        //System.out.println(ball.getX());



        stage.setScene(scene_);
        stage.setTitle("Pong");
        stage.show();
        if (Multiplayer_Mode){
            Server server=new Server(scene2_);
            Thread t=new Thread(server);
            t.start();


        }
    }
    public void Paddle_movement()
    {
        scene2_.setOnKeyPressed(keyEvent -> keys.put(keyEvent.getCode(),true));
        scene2_.setOnKeyReleased(keyEvent -> {keys.put(keyEvent.getCode(),false);});
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }
    public boolean isPressed(KeyCode key)
    {
        return keys.getOrDefault(key,false);
    }
    public void update()
    {


        if (isPressed(KeyCode.S)) {
            paddle1.MoveDown();
        }
        if (isPressed(KeyCode.W)) {
            paddle1.MoveUp();
            System.out.println(paddle1.getX());
        }
        if (isPressed(KeyCode.UP)) {
            paddle2_.MoveUp();
        }
        if (isPressed(KeyCode.DOWN)) {
            paddle2_.MoveDown();

        }

    }

    public void Ball_Movement() throws IOException {
        ball.Movement();

    }




};
