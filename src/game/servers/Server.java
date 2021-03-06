package game.servers;

import game.Constants;
import game.GameLayout;
import game.objects.Paddle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class Server implements Runnable {


    String data;

    Scene scene;
    Rectangle rect;
    String[] data_=new String[3];
    Circle ball;
    Paddle paddle;

    public Server(Scene scene,Paddle paddle){
        this.paddle=paddle;
       rect=new Rectangle(60,50, Constants.paddle_width,Constants.paddle_height);
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
        int iter=0;
        while(true)
        {
            iter++;
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                serverSocket.receive(receivePacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] bytearray=receivePacket.getData();
            ByteBuffer bb = ByteBuffer.wrap(bytearray);
            double[] doubles = new double[bytearray.length/8];
            for(int i = 0; i < doubles.length; i++) {
                doubles[i] = BigDecimal.valueOf(bb.getDouble()).setScale(3, RoundingMode.HALF_UP)
                        .doubleValue();


            }
           // System.out.print(doubles[1]+" "+doubles[2]);
            //System.out.println();
            rect.setTranslateY(doubles[0]);
            ball.setTranslateX(doubles[1]);
            ball.setTranslateY(doubles[2]);
           // data_=data.split("")
           /* data = new String( receivePacket.getData());
            System.out.println("iterace"+" "+iter);
            System.out.println("RECEIVED: " + data);
            int k=0;
            for (int i=0;i< data.length();i++){
            if (data.charAt(i)=='*'){k++;i++;}
            data_[k]+=data.charAt(i);
            //System.out.print(data.charAt(i));
            }
            data_[0]=data_[0].replaceFirst("null","");
            data_[1]=data_[1].replaceFirst("null","");
            data_[2]=data_[2].replaceFirst("null","");
            /*System.out.print(data_[0]+" "+data_[1]+" "+data_[2]);
            rect.setTranslateY(Double.parseDouble(data_[0]));
            ball.setTranslateX(Double.parseDouble(data_[1]));
            ball.setTranslateY(Double.parseDouble(data_[2]));*/

             //Thread.sleep(50);


            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String capitalizedSentence = Double.toString(paddle.getY());
            sendData = capitalizedSentence.getBytes();
            System.out.print("sent "+capitalizedSentence);
            System.out.println();
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
          //  serverSocket.close();
        }
    }

    }



