package sample;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.BlockingQueue;

public class Data_to_send {
    //private final BlockingQueue<Integer> queue;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;
    String data_in_string_form;
    public Data_to_send(Paddle paddle1, Paddle paddle2, Ball ball, Score score) {
        this.paddle1 = paddle1;
        this.paddle2 = paddle2;
        this.ball = ball;
        this.score = score;
        this.Convert_to_string();
    }
    public void Convert_to_string(){
        data_in_string_form=Double. toString(paddle1.getY());
    }

    public String getData_in_string_form() {
        return data_in_string_form;
    }
}
