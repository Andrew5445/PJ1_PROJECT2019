package game.servers;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main_client extends Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        GameLayout_client game2 = new GameLayout_client(stage, true);
    }
}



