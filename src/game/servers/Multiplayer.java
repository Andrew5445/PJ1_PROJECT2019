package game.servers;

import game.objects.Ball;
import game.objects.Paddle;
import game.objects.Score;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

public class Multiplayer implements Runnable {
    DatagramSocket clientSocket;
    DatagramPacket receivePacket;
    DatagramPacket sendPacket;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;
    ByteBuffer bb;
    ByteBuffer bb2;
    double[] da;
    byte[] sendData;
    byte[] receiveData;

    public Multiplayer(Paddle paddle1, Ball ball, Paddle paddle2, Score score) throws IOException {
        this.score = score;
        this.paddle1 = paddle1;
        this.paddle2 = paddle2;
        this.ball = ball;
        da = new double[5];
        receiveData = new byte[8];
        sendData = new byte[1024];
    }

    public void run() {


        while (true) {
            clientSocket = null;
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

            da[0] = paddle1.getY();
            da[1] = ball.X();
            da[2] = ball.Y();
            da[3] = score.getPlayer1();
            da[4] = score.getPlayer2();
            bb = ByteBuffer.allocate(da.length * 8);
            for (double d : da) {
                bb.putDouble(d);
            }
            System.out.println();
            sendData = bb.array();

            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            try {
                clientSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int port = sendPacket.getPort();
            receivePacket = new DatagramPacket(receiveData, receiveData.length, IPAddress, port);
            try {
                clientSocket.receive(receivePacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            receiveData = receivePacket.getData();
            bb2 = bb2.allocate(8);
            bb2.put(receiveData);
            bb2.flip();
            paddle2.setY(bb2.getDouble());
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
