package game.servers;

import game.Constants;
import game.objects.Ball;
import game.objects.Paddle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
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
    double[]da;
    byte[]data_in_bytes;
    public Multiplayer(Paddle paddle1, Ball ball, Scene scene) throws IOException {
        this.scene=scene;
        this.paddle1=paddle1;
        this.ball=ball;
        rect=new Rectangle(940,50, Constants.paddle_width,Constants.paddle_height);
        //this.paddle2=paddle;
        rect.setFill(Color.WHITE);


        Group root=(Group)this.scene.getRoot();
        root.getChildren().addAll(rect);
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
            da=new double[3];
            da[0]=paddle1.getY();
            da[1]=ball.X();
            da[2]=ball.Y();
            ByteBuffer bb = ByteBuffer.allocate(da.length*8);

            for(double d : da) {
               // System.out.print(d);
                bb.putDouble(d);
            }
            System.out.println();
            byte[] sendData =  bb.array();
            byte[] receiveData = new byte[1024];
            String sentence = null;
           // sentence=(Double.toString(paddle1.getY())+"*"+Double.toString(ball.X())+"*"+Double.toString(ball.Y()));

            //System.out.println(Arrays.toString(sendData));
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            try {
                clientSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int port = sendPacket.getPort();
            DatagramPacket receivePacket=new DatagramPacket(receiveData,receiveData.length,IPAddress,port);
            try {
                clientSocket.receive(receivePacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String received=new String(receivePacket.getData());
            System.out.print("received"+" "+received);
            rect.setTranslateY(Double.parseDouble(received));
            try {
               Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           // clientSocket.close();
        }
    }

}
