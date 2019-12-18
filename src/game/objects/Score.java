package game.objects;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score {
    private Integer player1;
    private Integer player2;
    private Text text;

    public Score(Scene scene) {
        Group root = (Group) scene.getRoot();
        player1 = 0;
        player2 = 0;
        text = new Text(450, 50, player1.toString() + " " + ":" + " " + player2.toString());
        text.setFont(new Font(50));
        text.setFill(Color.WHITE);
        root.getChildren().add(text);
    }

    public void Add_point_to_player1() {
        player1 += 1;
        Update_score();
    }

    public void Add_point_to_player2() {
        player2 += 1;
        Update_score();
    }

    public void Update_score() {
        this.text.setFill(Color.WHITE);
        this.text.setText(player1.toString() + " " + ":" + " " + player2.toString());

    }

    public double getPlayer1() {
        return player1;
    }

    public double getPlayer2() {
        return player2;
    }

    public void setPlayer1(double point) {
        player1 = (int) point;
        Update_score();
    }

    public void setPlayer2(double point) {
        player2 = (int) point;
        Update_score();
    }
}
