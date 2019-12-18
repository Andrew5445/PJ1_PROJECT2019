package game;

import game.objects.*;
import game.servers.Multiplayer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;


public class GameLayout {
    HashMap<KeyCode, Boolean> keys = new HashMap<>();
    Scene scene2;
    Scene scene;
    Group layout1;
    Group layout2;
    Paddle paddle1;
    Paddle paddle2;
    Score score;
    Ball ball;

    public GameLayout(Stage stage, boolean Multiplayer) throws IOException {
        stage.setResizable(false);
        layout1 = new Group();
        Button button1 = new Button("Click to start game");
        button1.setOnAction(e -> stage.setScene(scene2));
        button1.setStyle("-fx-border-color: white;" +
                "-fx-background-color: black;" +
                "-fx-font-size: 50px;" + "-fx-text-fill:white");
        layout1.setStyle("-fx-background-color: black;");
        layout1.getChildren().addAll(button1);
        button1.setPrefWidth(1001);
        button1.setPrefHeight(501);
        scene = new Scene(layout1, Constants.scene_width, Constants.scene_height);
        layout2 = new Group();
        scene2 = new Scene(layout2, Constants.scene_width, Constants.scene_height);
        scene2.setFill(Color.BLACK);
        paddle1 = new Paddle(60, 50, scene2);
        paddle2 = new Paddle(940, 50, scene2);
        score = new Score(scene2);
        ball = new Ball(scene2, paddle1, paddle2, score);
        if (Multiplayer) {
            Multiplayer multiplayer = new Multiplayer(paddle1, ball, paddle2, score);
            Thread thread = new Thread(multiplayer);
            thread.start();
        }
        Ball_Movement();
        Paddle_movement();
        stage.setScene(scene);
        stage.setTitle("Server");
        stage.show();
    }

    public void Paddle_movement() {
        scene2.setOnKeyPressed(keyEvent -> keys.put(keyEvent.getCode(), true));
        scene2.setOnKeyReleased(keyEvent -> {
            keys.put(keyEvent.getCode(), false);
        });
        Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(.060),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        update();
                    }
                });
        tl.getKeyFrames().add(moveBall);
        tl.play();
    }

    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    public void update() {
        if (isPressed(KeyCode.S)) {
            paddle1.MoveDown();
        }
        if (isPressed(KeyCode.W)) {
            paddle1.MoveUp();
        }
    }

    public void Ball_Movement() throws IOException {
        ball.Movement();
    }
};
