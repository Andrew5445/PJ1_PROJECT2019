package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class GameServer extends Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        launch(args);
    }
    public void start(Stage stage) throws Exception{
        GameLayout_server game2=new GameLayout_server(stage,true);


    }
}



