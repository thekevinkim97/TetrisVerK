package App;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Tetris extends Application {

    public static final int moveSpeed = 25;
    public static final int blockSize = 25;
    public static int xMax = blockSize * 10;
    public static int yMax = blockSize * 20;
    public static int [][] playGrid = new int[xMax/blockSize][yMax/blockSize];

    private static Pane group = new Pane();
    private static Form object;
    private static Scene scene = new Scene(group, xMax + 150, yMax);

    public static int score = 0;
    private static boolean game = true;
    private static Form nextObj = Controller.makeRect();

    @Override
    public void start(Stage arg0) throws Exception {

    }

}
