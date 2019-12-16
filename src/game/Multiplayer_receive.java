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

public class Multiplayer_receive implements Runnable {
    //private BlockingQueue<Integer> queue;

    DatagramSocket socket;
    DatagramPacket packet;
    String data;
    Data_to_send object;
    Paddle paddle1;
    Ball ball;
    Rectangle rect;
    Scene scene;
    Paddle paddle2;
    byte[]data_in_bytes;
    public Multiplayer_receive(Scene scene,Paddle paddle) throws IOException {
        this.scene=scene;
        //rect=new Rectangle(940,50,5,40);
       this.paddle2=paddle;
//        rect.setFill(Color.WHITE);


     /*   Group root=(Group)this.scene.getRoot();
        root.getChildren().addAll(rect);*/
    }
    public void run () {
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(9876);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        byte[] receiveData = new byte[1024];
        while(true)
        {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                serverSocket.receive(receivePacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // data_=data.split("")
            data = new String( receivePacket.getData());
            //rect.setTranslateY(Double.parseDouble(data));
            paddle2.setY(Double.parseDouble(data));
            System.out.println("Received:"+data);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
