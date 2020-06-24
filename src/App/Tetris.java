package App;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

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
    public static int totalLines = 0;
    public static Text scoreText;
    public static Text totalLinesText;
    private static boolean game = true;
    private static Form nextObj = Controller.makeRect();

    public static int top = 0;

    public void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage arg0) throws Exception {
        for (int[] a: playGrid) {
            Arrays.fill(a, 0);
        }

        createScoreText();
        createStage(arg0);
    }

    public void createScoreText() {
        Line line = new Line(xMax, 0, xMax, yMax);
        scoreText = new Text("Score: ");
        scoreText.setStyle("-fx-font: 24 arials;");
        scoreText.setY(50);
        scoreText.setX(xMax + 5);
        scoreText.setFill(Color.RED);

        totalLinesText = new Text("Total Lines: ");
        scoreText.setStyle("-fx-font: 24 arials;");
        scoreText.setY(50);
        scoreText.setX(xMax + 5);
        scoreText.setFill(Color.BLUEVIOLET);

        group.getChildren().addAll(scoreText, line);
    }

    public void createStage(Stage stage) {
        Form a = nextObj;
        group.getChildren().addAll(a.blockA, a.blockB, a.blockC, a.blockD);
        moveOnKeyPress(a);
        object = a;
        nextObj = Controller.makeRect();
        stage.setScene(scene);
        stage.setTitle("T E T R I S");
        stage.show();
    }

    public void timer() {
        Timer fall = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (object.blockA.getY() == 0 || object.blockB.getY() == 0 || object.blockC.getY() == 0 || object.blockD.getY() == 0) {
                            top++;
                        }
                        else {
                            top = 0;
                        }

                        if (top == 2) {
                            gameOver();
                        }
                        else if (top == 15) {
                            System.exit(0);
                        }

                        if (game) {
                            Controller.shiftBlockDown(object, group, score, totalLines, nextObj, object);
                            scoreText.setText("Score: " + Integer.toString(score));
                            totalLinesText.setText("Total Lines: " + Integer.toString(totalLines));
                        }
                    }
                });
            }
        };

        fall.schedule(task, 0, 300);

    }

    public void gameOver() {
        Text gameOver = new Text("G A M E   O V E R");
        gameOver.setFill(Color.BLUEVIOLET);
        gameOver.setStyle("-fx-font: 80 arial;");
        gameOver.setX(10);
        gameOver.setY(250);
        group.getChildren().add(gameOver);
        game = false;
    }

    public void moveOnKeyPress(Form form) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case RIGHT:
                        Controller.moveRight(form);
                        break;
                    case UP:
                        Controller.moveTurn(form);
                        break;
                    case DOWN:
                        Controller.shiftBlockDown(form, group, score, totalLines, nextObj, object);
                        break;
                    case LEFT:
                        Controller.moveLeft(form);
                        break;
                }
            }
        });
    }

}
