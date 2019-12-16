package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class Multiplayer implements Runnable {
    //private BlockingQueue<Integer> queue;

    DatagramSocket socket;
    DatagramPacket packet;
    String data;
    Paddle paddle1;
    Ball ball;
    Rectangle rect;
    Scene scene;
    byte[]data_in_bytes;
    public Multiplayer(Paddle paddle1, Ball ball, Scene scene) throws IOException {
        this.scene=scene;
        this.paddle1=paddle1;
        this.ball=ball;
    }
    public void run () {
        //this.data=this.object.getData_in_string_form();
        while (true) {


            //get input from user
            // get a datagram socket
            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = null;
            try {
                clientSocket = new DatagramSocket();
            } catch (SocketException e) {
                e.printStackTrace();
            }
            InetAddress IPAddress = null;
            try {
                IPAddress = InetAddress.getByName("localhost");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            String sentence = null;
                sentence=(Double.toString(paddle1.getY())+" "+Double.toString(ball.X())+" "+Double.toString(ball.Y()));
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            try {
                clientSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
               Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clientSocket.close();
        }
    }

}
