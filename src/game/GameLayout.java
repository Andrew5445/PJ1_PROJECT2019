package game;

import game.objects.*;
import game.servers.Multiplayer;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class GameLayout {

private HashMap<KeyCode, Boolean> keys = new HashMap<>();
Scene scene2;
static Scene scene;
HBox start_screen;
StackPane playground;
Paddle paddle1;
Paddle paddle2;
Score score;
Ball ball;
Data_to_send object;

    static double dx = Constants.ball_speed;
    static double dy = Constants.ball_speed;
    public GameLayout(Stage stage,boolean Multiplayer_Mode) throws IOException {
        stage.setResizable(false);
        Button button1=new Button("Click to start game");
        button1.setOnAction(e-> stage.setScene(scene2));
        button1.setStyle("-fx-border-color: white;" +
                "-fx-background-color: black;" +
                "-fx-font-size: 50px;"+"-fx-text-fill:white");

        HBox layout1=new HBox();
        layout1.setAlignment(Pos.CENTER);
        layout1.setStyle("-fx-background-color: black;");
        layout1.getChildren().addAll(button1);
        button1.setPrefWidth(1001);
        button1.setPrefHeight(501);
        scene=new Scene(layout1, Constants.scene_width, Constants.scene_height);
        Group layout2=new Group();
        scene2=new Scene(layout2, Constants.scene_width, Constants.scene_height);
        scene2.setFill(Color.BLACK);
        paddle1=new Paddle(60,50,scene2);
       // paddle2=new Paddle(940,50,scene2);
      //  score=new Score(scene2);
        ball=new Ball(scene2,paddle1,paddle2,score);
        Ball_Movement();
        Paddle_movement();
       //System.out.println(paddle1.getY());



        stage.setScene(scene);
        stage.setTitle("Server");
        stage.show();
        if (Multiplayer_Mode){

            Multiplayer multiplayer=new Multiplayer(paddle1,ball,scene2);
            Thread thread=new Thread(multiplayer);

           /* Multiplayer_receive rec=new Multiplayer_receive(scene2);
            Thread t2=new Thread(rec);*/
            thread.start();
           // t2.start();

        }
    }
    public void Paddle_movement()
    {
        scene2.setOnKeyPressed(keyEvent -> keys.put(keyEvent.getCode(),true));
        scene2.setOnKeyReleased(keyEvent -> {keys.put(keyEvent.getCode(),false);});


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
            //System.out.println(paddle1.getX());
        }
       /* if (isPressed(KeyCode.UP)) {
                paddle2.MoveUp();
        }
        if (isPressed(KeyCode.DOWN)) {
                paddle2.MoveDown();

        }*/

    }

    public void Ball_Movement() throws IOException {
        ball.Movement();

    }




};
