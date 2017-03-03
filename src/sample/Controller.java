package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class Controller {

    @FXML private javafx.scene.control.Label MessageLabel;
    @FXML private Canvas MazeCanvas;

    @FXML public void initialize()
    {

        Maze m = new Maze(MazeCanvas.getGraphicsContext2D());

        m.PaintCanvas();



    }

    @FXML protected void Mouse_Clicked() {
        MessageLabel.setText("clicked to canvas");
    }


    @FXML protected void Button_Clicked()
    {
        Platform.exit();
    }

}
