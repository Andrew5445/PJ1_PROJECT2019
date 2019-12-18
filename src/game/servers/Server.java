package game.servers;

import game.objects.Paddle;
import game.objects.Score;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class Server implements Runnable {
    DatagramSocket serverSocket;
    DatagramPacket receivePacket;
    DatagramPacket sendPacket;
    Score score;
    Scene scene;
    Circle ball;
    Paddle paddle1;
    Paddle paddle2;
    ByteBuffer bb;
    ByteBuffer bb2;

    public Server(Scene scene, Paddle paddle, Score score, Paddle paddle2) {
        this.paddle1 = paddle;
        this.paddle2 = paddle2;
        this.score = score;
        ball = new Circle(500, 250, 10);
        ball.setFill(Color.WHITE);
        this.scene = scene;
        Group root = (Group) this.scene.getRoot();
        root.getChildren().addAll(ball);
    }


    public void run() {

        try {
            serverSocket = new DatagramSocket(32976);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[8];
        while (true) {

            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                serverSocket.receive(receivePacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] bytearray = receivePacket.getData();
            bb = ByteBuffer.wrap(bytearray);
            double[] doubles = new double[bytearray.length / 8];
            for (int i = 0; i < doubles.length; i++) {
                doubles[i] = BigDecimal.valueOf(bb.getDouble()).setScale(3, RoundingMode.HALF_UP)
                        .doubleValue();
            }
            paddle2.setY(doubles[0]);
            ball.setTranslateX(doubles[1]);
            ball.setTranslateY(doubles[2]);
            score.setPlayer1(doubles[3]);
            score.setPlayer2(doubles[4]);

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            bb2 = bb2.allocate(8);
            bb2.putDouble(paddle1.getY());
            sendData = bb2.array();
            sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, port);
            try {
                serverSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}



