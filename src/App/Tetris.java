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
    public static int xMax = blockSize * 12;
    public static int yMax = blockSize * 24;
    public static int [][] playGrid = new int[xMax/blockSize][yMax/blockSize];

    private static Pane group = new Pane();
    private static Form object;
    private static Scene scene = new Scene(group, xMax + 150, yMax);

    public static int top = 0;
    public static int score = 0;
    public static int totalLines = 0;

    private static boolean game = true;
    private static Form nextObj = Controller.makeRect();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        for (int[] a : playGrid) {
            Arrays.fill(a, 0);
        }
        createScoreText();
        createStage(stage);

    }

    public void createScoreText() {
        Line line = new Line(xMax, 0, xMax, yMax);

        Text scoreText = new Text("Score: ");
        scoreText.setStyle("-fx-font: 24 arials;");
        scoreText.setY(50);
        scoreText.setX(xMax + 5);
        scoreText.setFill(Color.RED);

        Text totalLinesText = new Text("Total Lines: ");
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
                            Text gameOver = new Text("G A M E   O V E R");
                            gameOver.setFill(Color.BLUEVIOLET);
                            gameOver.setStyle("-fx-font: 80 arial;");
                            gameOver.setX(10);
                            gameOver.setY(250);
                            group.getChildren().add(gameOver);
                            game = false;
                        }

                        //Exit
                        if (top == 15) {
                            System.exit(0);
                        }

                        if (game) {
                            moveDown(object);
                            //scoreText totalLinesText
                        }
                    }
                });
            }
        };
        fall.schedule(task, 0, 300);
    }

    private void moveOnKeyPress(Form form) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
                        moveTurn(form);
                        break;
                    case RIGHT:
                        Controller.moveRight(form);
                        break;
                    case DOWN:
                        moveDown(form);
                        break;
                    case LEFT:
                        Controller.moveLeft(form);
                        break;
                }
            }
        });
    }


    //Rotate
    private void moveTurn(Form form) {
        int f = form.form;
        Rectangle blockA = form.blockA;
        Rectangle blockB = form.blockB;
        Rectangle blockC = form.blockC;
        Rectangle blockD = form.blockD;
        switch (form.getName()) {
            case "j":
                if ((f == 1 && checkBlock(blockA, 1, -1) && checkBlock(blockC, -1, -1) && checkBlock(blockD, -2, -2)) || (f == 2 && checkBlock(blockA, -1, -1) && checkBlock(blockC, -1, 1) && checkBlock(blockD, -2, 2)) || (f == 3 && checkBlock(blockA, -1, 1) && checkBlock(blockC, 1, 1) && checkBlock(blockD, 2, 2)) || (f == 4 && checkBlock(blockA, 1, 1) && checkBlock(blockC, 1, -1) && checkBlock(blockD, 2, -2))) {
                    rotateJBlock(form, f);
                }
                break;
            case "l":
                if ((f == 1 && checkBlock(blockA, 1, -1) && checkBlock(blockB, 2, 2) && checkBlock(blockC, 1, 1)) || (f == 2 && checkBlock(blockA, -1, -1) && checkBlock(blockB, 2, -2) && checkBlock(blockC, 1, -1)) || (f == 3 && checkBlock(blockA, -1, 1) && checkBlock(blockB, -2, -2) && checkBlock(blockC, -1, -1)) || (f == 4 && checkBlock(blockA, 1, 1) && checkBlock(blockB, -2, 2) && checkBlock(blockC, -1, 1))) {
                    rotateLBlock(form, f);
                }
                break;
            case "o":
                break;
            case "s":
                if ((f == 1 && checkBlock(blockA, -1, -1) && checkBlock(blockC, -1, 1) && checkBlock(blockD, 0, 2)) || (f == 2 && checkBlock(blockA, 1, 1) && checkBlock(blockC, 1, -1) && checkBlock(blockD, 0, -2)) || (f == 3 && checkBlock(blockA, -1, -1) && checkBlock(blockC, -1, 1) && checkBlock(blockD, 0, 2)) || (f == 4 && checkBlock(blockA, 1, 1) && checkBlock(blockC, 1, -1) && checkBlock(blockD, 0, -2))) {
                    rotateSBlock(form, f);
                }
                break;
            case "t":
                if ((f == 1 && checkBlock(blockA, 1, 1) && checkBlock(blockC, -1, 1) && checkBlock(blockD, -1, -1)) || (f == 2 && checkBlock(blockA, 1, -1) && checkBlock(blockC, 1, 1) && checkBlock(blockD, -1, 1)) || (f == 3 && checkBlock(blockA, -1, -1) && checkBlock(blockC, 1, -1) && checkBlock(blockD, 1, 1)) || (f == 4 && checkBlock(blockA, -1, 1) && checkBlock(blockC, -1, -1) && checkBlock(blockD, 1, -1))) {
                    rotateTBlock(form, f);
                }
                break;
            case "z":
                if (((f == 1 || f == 3) && checkBlock(blockB, 1, 1) && checkBlock(blockC, -1, 1) && checkBlock(blockD, -2, 0)) || ((f == 2 || f == 4) && checkBlock(blockB, -1, -1) && checkBlock(blockC, 1, -1) && checkBlock(blockD, 2, 0))) {
                    rotateZBlock(form, f);
                }
                break;
            case "i":
                //(f == 1 || f == 3) && (checkBlock(blockA, 2, 2) && checkBlock(blockB, 1, 1) && checkBlock(blockD, -1, -1))
                //(f == 2 || f == 4) && (checkBlock(blockA, -2, -2) && checkBlock(blockB, -1, -1) && checkBlock(blockD, 1, 1))
                if (((f == 1 || f == 3) && (checkBlock(blockA, 2, 2) && checkBlock(blockB, 1, 1) && checkBlock(blockD, -1, -1))) || ((f == 2 || f == 4) && (checkBlock(blockA, -2, -2) && checkBlock(blockB, -1, -1) && checkBlock(blockD, 1, 1)))) {
                    rotateIBlock(form, f);
                }
                break;
        }
    }

    //If can't shift down
    private void moveDown(Form form) {
        if (form.blockA.getY() == yMax - blockSize || form.blockB.getY() == yMax - blockSize || form.blockC.getY() == yMax - blockSize || form.blockD.getY() == yMax - blockSize || moveBlock(form)) {
            playGrid[(int) form.blockA.getX() / blockSize][(int) form.blockA.getY() / blockSize] = 1;
            playGrid[(int) form.blockB.getX() / blockSize][(int) form.blockB.getY() / blockSize] = 1;
            playGrid[(int) form.blockC.getX() / blockSize][(int) form.blockC.getY() / blockSize] = 1;
            playGrid[(int) form.blockD.getX() / blockSize][(int) form.blockD.getY() / blockSize] = 1;

            deleteCompleteRows(group);

            Form a = nextObj;
            nextObj = Controller.makeRect();
            object = a;
            group.getChildren().addAll(a.blockA, a.blockB, a.blockC, a.blockD);
            moveOnKeyPress(a);
        }

        //Moving one block down if there is space beneath
        if (form.blockA.getY() + moveSpeed < yMax && form.blockB.getY() + moveSpeed < yMax && form.blockC.getY() + moveSpeed < yMax && form.blockD.getY() + moveSpeed < yMax) {
            int moveA = playGrid[(int) form.blockA.getX() / blockSize][((int) form.blockA.getY() / blockSize) + 1];
            int moveB = playGrid[(int) form.blockB.getX() / blockSize][((int) form.blockB.getY() / blockSize) + 1];
            int moveC = playGrid[(int) form.blockC.getX() / blockSize][((int) form.blockC.getY() / blockSize) + 1];
            int moveD = playGrid[(int) form.blockD.getX() / blockSize][((int) form.blockD.getY() / blockSize) + 1];
            if (moveA == 0 && moveA == moveB && moveB == moveC && moveC == moveD) {
                form.blockA.setY(form.blockA.getY() + moveSpeed);
                form.blockB.setY(form.blockB.getY() + moveSpeed);
                form.blockC.setY(form.blockC.getY() + moveSpeed);
                form.blockD.setY(form.blockD.getY() + moveSpeed);
            }
        }
    }

    private void deleteCompleteRows(Pane pane) {
        ArrayList<Node> rects = new ArrayList<Node>();
        ArrayList<Integer> lines = new ArrayList<Integer>();
        ArrayList<Node> newRects = new ArrayList<Node>();
        int fullLines = 0;

        for (int i = 0; i < playGrid[0].length; i++) {
            for (int j = 0; j < playGrid.length; j++) {
                if (playGrid[j][i] == 1) {
                    fullLines++;
                }
            }

            if (fullLines == playGrid.length) {
                lines.add(i);
            }
            fullLines = 0;
        }

        //deleting block from row
        if (lines.size() > 0) {
            do {
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle) {
                        rects.add(node);
                    }
                }
                score += 25;
                totalLines++;

                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;

                    if (a.getY() == lines.get(0) * blockSize) {
                        playGrid[(int) a.getX() / blockSize][(int) a.getY() / blockSize] = 0;
                        pane.getChildren().remove(node);
                    }
                    else {
                        newRects.add(node);
                    }
                }

                for (Node node : newRects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() < lines.get(0) * blockSize) {
                        playGrid[(int) a.getX() / blockSize][(int) a.getY() / blockSize] = 0;
                        a.setY(a.getY() + blockSize);
                    }
                }

                lines.remove(0);
                rects.clear();
                newRects.clear();

                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle) {
                        rects.add(node);
                    }
                }

                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    try {
                        playGrid[(int) a.getX() / blockSize][(int) a.getY() / blockSize] = 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }

                rects.clear();

            } while (lines.size() > 0);
        }
    }

    private boolean checkBlock(Rectangle block, int x, int y) {
        boolean yB = false;
        boolean xB = false;
        if (x >= 0) {
            xB = block.getX() + x*moveSpeed <= xMax - blockSize;
        }
        else {
            xB = block.getX() + x*moveSpeed >= 0;
        }
        if (y >= 0) {
            yB = block.getY() + y*moveSpeed > 0;
        }
        else {
            yB = block.getY() + y*moveSpeed < yMax;
        }

        return xB && yB && playGrid[((int)block.getX()/blockSize) + x][((int)block.getY()/blockSize) - y] == 0;
    }

    private static boolean moveBlock(Form form) {
        if ((playGrid[(int) form.blockA.getX() / blockSize][((int) form.blockA.getY() / blockSize) + 1] == 1) || (playGrid[(int) form.blockB.getX() / blockSize][((int) form.blockB.getY() / blockSize) + 1] == 1) || (playGrid[(int) form.blockC.getX() / blockSize][((int) form.blockC.getY() / blockSize) + 1] == 1) || (playGrid[(int) form.blockD.getX() / blockSize][((int) form.blockD.getY() / blockSize) + 1] == 1)) {
            return true;
        }
        else {
            return false;
        }
    }


    //Shifts individual block pieces
    private void shiftBlockUp(Rectangle rect) {
        if (rect.getY() - moveSpeed > 0) {
            rect.setY(rect.getY() - moveSpeed);
        }
    }

    private void shiftBlockRight(Rectangle rect) {
        if (rect.getX() + moveSpeed <= xMax - blockSize) {
            rect.setX(rect.getX() + moveSpeed);
        }
    }

    private void shiftBlockDown(Rectangle rect) {
        if (rect.getY() + moveSpeed < yMax) {
            rect.setY(rect.getY() + moveSpeed);
        }
    }

    private void shiftBlockLeft(Rectangle rect) {
        if (rect.getX() - moveSpeed >= 0) {
            rect.setX(rect.getX() - moveSpeed);
        }
    }


    //Handles physical rotation for each type of block shape
    private void rotateJBlock(Form form, int f) {
        if (f == 1) {
            shiftBlockRight(form.blockA);
            shiftBlockDown(form.blockA);
            shiftBlockDown(form.blockC);
            shiftBlockLeft(form.blockC);
            shiftBlockDown(form.blockD);
            shiftBlockDown(form.blockD);
            shiftBlockLeft(form.blockD);
            shiftBlockLeft(form.blockD);
            form.changeForm();
        }
        else if (f == 2) {
            shiftBlockDown(form.blockA);
            shiftBlockLeft(form.blockA);
            shiftBlockLeft(form.blockC);
            shiftBlockUp(form.blockC);
            shiftBlockLeft(form.blockD);
            shiftBlockLeft(form.blockD);
            shiftBlockUp(form.blockD);
            shiftBlockUp(form.blockD);
            form.changeForm();
        }
        else if (f == 3) {
            shiftBlockLeft(form.blockA);
            shiftBlockUp(form.blockA);
            shiftBlockUp(form.blockC);
            shiftBlockRight(form.blockC);
            shiftBlockUp(form.blockD);
            shiftBlockUp(form.blockD);
            shiftBlockRight(form.blockD);
            shiftBlockRight(form.blockD);
            form.changeForm();
        }
        else if (f == 4) {
            shiftBlockUp(form.blockA);
            shiftBlockRight(form.blockA);
            shiftBlockRight(form.blockC);
            shiftBlockDown(form.blockC);
            shiftBlockRight(form.blockD);
            shiftBlockRight(form.blockD);
            shiftBlockDown(form.blockD);
            shiftBlockDown(form.blockD);
            form.changeForm();
        }
    }

    private void rotateLBlock(Form form, int f) {
        if (f == 1) {
            shiftBlockRight(form.blockA);
            shiftBlockDown(form.blockA);
            shiftBlockUp(form.blockB);
            shiftBlockUp(form.blockB);
            shiftBlockRight(form.blockB);
            shiftBlockRight(form.blockB);
            shiftBlockUp(form.blockC);
            shiftBlockRight(form.blockC);
            form.changeForm();
        }
        else if (f == 2) {
            shiftBlockUp(form.blockA);
            shiftBlockLeft(form.blockA);
            shiftBlockRight(form.blockB);
            shiftBlockRight(form.blockB);
            shiftBlockDown(form.blockB);
            shiftBlockDown(form.blockB);
            shiftBlockRight(form.blockC);
            shiftBlockDown(form.blockC);
            form.changeForm();
        }
        else if (f == 3) {
            shiftBlockLeft(form.blockA);
            shiftBlockUp(form.blockA);
            shiftBlockDown(form.blockB);
            shiftBlockDown(form.blockB);
            shiftBlockLeft(form.blockB);
            shiftBlockLeft(form.blockB);
            shiftBlockDown(form.blockC);
            shiftBlockLeft(form.blockC);
            form.changeForm();
        }
        else if (f == 4) {
            shiftBlockUp(form.blockA);
            shiftBlockRight(form.blockA);
            shiftBlockLeft(form.blockB);
            shiftBlockLeft(form.blockB);
            shiftBlockUp(form.blockB);
            shiftBlockUp(form.blockB);
            shiftBlockLeft(form.blockC);
            shiftBlockUp(form.blockC);
            form.changeForm();
        }
    }

    private void rotateSBlock(Form form, int f) {
        if (f == 1 || f == 3) {
            shiftBlockDown(form.blockA);
            shiftBlockLeft(form.blockA);
            shiftBlockLeft(form.blockC);
            shiftBlockUp(form.blockC);
            shiftBlockUp(form.blockD);
            shiftBlockUp(form.blockD);
            form.changeForm();
        }
        else if (f == 2 || f == 4) {
            shiftBlockUp(form.blockA);
            shiftBlockRight(form.blockA);
            shiftBlockRight(form.blockC);
            shiftBlockDown(form.blockC);
            shiftBlockDown(form.blockD);
            shiftBlockDown(form.blockD);
            form.changeForm();
        }
    }

    private void rotateTBlock(Form form, int f) {
        if (f == 1) {
            shiftBlockUp(form.blockA);
            shiftBlockRight(form.blockA);
            shiftBlockLeft(form.blockC);
            shiftBlockUp(form.blockC);
            shiftBlockDown(form.blockD);
            shiftBlockLeft(form.blockD);
            form.changeForm();
        }
        else if (f == 2) {
            shiftBlockRight(form.blockA);
            shiftBlockDown(form.blockA);
            shiftBlockUp(form.blockC);
            shiftBlockRight(form.blockC);
            shiftBlockLeft(form.blockD);
            shiftBlockUp(form.blockD);
            form.changeForm();
        }
        else if (f == 3) {
            shiftBlockDown(form.blockA);
            shiftBlockLeft(form.blockA);
            shiftBlockRight(form.blockC);
            shiftBlockDown(form.blockC);
            shiftBlockUp(form.blockD);
            shiftBlockRight(form.blockD);
            form.changeForm();
        }
        else if (f == 4) {
            shiftBlockLeft(form.blockA);
            shiftBlockUp(form.blockA);
            shiftBlockDown(form.blockC);
            shiftBlockLeft(form.blockC);
            shiftBlockRight(form.blockD);
            shiftBlockDown(form.blockD);
            form.changeForm();
        }
    }

    private void rotateZBlock(Form form, int f) {
        if (f == 1 || f == 3) {
            shiftBlockUp(form.blockB);
            shiftBlockRight(form.blockB);
            shiftBlockLeft(form.blockC);
            shiftBlockUp(form.blockC);
            shiftBlockLeft(form.blockD);
            shiftBlockLeft(form.blockD);
            form.changeForm();
        }
        else if (f == 2 || f == 4) {
            shiftBlockDown(form.blockB);
            shiftBlockLeft(form.blockB);
            shiftBlockRight(form.blockC);
            shiftBlockDown(form.blockC);
            shiftBlockRight(form.blockD);
            shiftBlockRight(form.blockD);
            form.changeForm();
        }
    }

    private void rotateIBlock(Form form, int f) {
        if (f == 1 || f == 3) {
            shiftBlockUp(form.blockA);
            shiftBlockUp(form.blockA);
            shiftBlockRight(form.blockA);
            shiftBlockRight(form.blockA);
            shiftBlockUp(form.blockB);
            shiftBlockRight(form.blockB);
            shiftBlockDown(form.blockD);
            shiftBlockLeft(form.blockD);
            form.changeForm();
        }
        else if (f == 2 || f == 4) {
            shiftBlockDown(form.blockA);
            shiftBlockDown(form.blockA);
            shiftBlockLeft(form.blockA);
            shiftBlockLeft(form.blockA);
            shiftBlockDown(form.blockB);
            shiftBlockLeft(form.blockB);
            shiftBlockUp(form.blockD);
            shiftBlockRight(form.blockD);
            form.changeForm();
        }
    }

}
