package game.servers;

import game.GameLayout;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server implements Runnable {

    String data;
    String data_1;
    String data_2;
    String data_3;
    Scene scene;
    Rectangle rect;
    String[] data_=new String[3];
    Circle ball;

    public Server(Scene scene){
       rect=new Rectangle(60,50,5,40);
       ball=new Circle(500,250,10);
       ball.setFill(Color.WHITE);
       rect.setFill(Color.WHITE);
        this.scene=scene;
        Group root=(Group)this.scene.getRoot();
        root.getChildren().addAll(rect,ball);
    }


    public void run ()  {


        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(9876);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
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
            data_=data.split(" ");
            rect.setTranslateY(Double.parseDouble(data_[0]));
            ball.setCenterX(Double.parseDouble(data_[1]));
            ball.setCenterY(Double.parseDouble(data_[2]));

             //Thread.sleep(50);

            System.out.println("RECEIVED: " + data);
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String capitalizedSentence = data.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, port);
            try {
                serverSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void start(Stage stage) throws Exception{
        GameLayout game=new GameLayout(stage,true);


    }
    }



