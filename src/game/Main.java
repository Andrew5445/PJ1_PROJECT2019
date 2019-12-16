package game;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        GameLayout game=new GameLayout(stage,true);


    }


    public static void main(String[] args) {
        launch(args);
    }
}
