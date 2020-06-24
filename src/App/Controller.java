package App;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class Controller {

    public static final int moveSpeed = Tetris.moveSpeed;
    public static final int blockSize = Tetris.blockSize;
    public static int xMax = Tetris.xMax;
    public static int yMax = Tetris.yMax;
    public static int [][] playGrid = Tetris.playGrid;
    public static Tetris tetris = new Tetris();

    //Move Right
    public static void moveRight (Form form) {
        if (form.blockA.getX() + moveSpeed <= xMax - blockSize && form.blockB.getX() + moveSpeed <= xMax - blockSize && form.blockC.getX() + moveSpeed <= xMax - blockSize && form.blockD.getX() + moveSpeed <= xMax - blockSize) {
            int moveA = playGrid[((int) form.blockA.getX() / blockSize) + 1][((int) form.blockA.getY() / blockSize)];
            int moveB = playGrid[((int) form.blockB.getX() / blockSize) + 1][((int) form.blockB.getY() / blockSize)];
            int moveC = playGrid[((int) form.blockC.getX() / blockSize) + 1][((int) form.blockC.getY() / blockSize)];
            int moveD = playGrid[((int) form.blockD.getX() / blockSize) + 1][((int) form.blockD.getY() / blockSize)];

            if (moveA == 0 && moveA == moveB && moveB == moveC && moveC == moveD) {
                form.blockA.setX(form.blockA.getX() + moveSpeed);
                form.blockB.setX(form.blockB.getX() + moveSpeed);
                form.blockC.setX(form.blockC.getX() + moveSpeed);
                form.blockD.setX(form.blockD.getX() + moveSpeed);
            }
        }
    }
    //Rotation
    public static void moveTurn (Form form) {
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
    //Move Left
    public static void moveLeft (Form form) {
        if (form.blockA.getX() - moveSpeed >= 0 && form.blockB.getX() - moveSpeed >= 0 && form.blockC.getX() - moveSpeed >= 0 && form.blockD.getX() - moveSpeed >= 0) {
            int moveA = playGrid[((int) form.blockA.getX() / blockSize) - 1][((int) form.blockA.getY() / blockSize)];
            int moveB = playGrid[((int) form.blockB.getX() / blockSize) - 1][((int) form.blockB.getY() / blockSize)];
            int moveC = playGrid[((int) form.blockC.getX() / blockSize) - 1][((int) form.blockC.getY() / blockSize)];
            int moveD = playGrid[((int) form.blockD.getX() / blockSize) - 1][((int) form.blockD.getY() / blockSize)];

            if (moveA == 0 && moveA == moveB && moveB == moveC && moveC == moveD) {
                form.blockA.setX(form.blockA.getX() - moveSpeed);
                form.blockB.setX(form.blockB.getX() - moveSpeed);
                form.blockC.setX(form.blockC.getX() - moveSpeed);
                form.blockD.setX(form.blockD.getX() - moveSpeed);
            }
        }
    }

    public static Form makeRect() {
        int block = (int) (Math.random() * 100);
        String name;

        Rectangle blockA = new Rectangle(blockSize -1, blockSize-1),
                blockB = new Rectangle(blockSize -1, blockSize-1),
                blockC = new Rectangle(blockSize -1, blockSize-1),
                blockD = new Rectangle(blockSize -1, blockSize-1);

        if (block < 15) {
            name = "j";
            return createJBlock(blockA, blockB, blockC, blockD, name);
        }
        else if (block < 30) {
            name = "l";
            return createLBlock(blockA, blockB, blockC, blockD, name);
        }
        else if (block < 45) {
            name = "o";
            return createOBlock(blockA, blockB, blockC, blockD, name);
        }
        else if (block < 60) {
            name = "s";
            return createSBlock(blockA, blockB, blockC, blockD, name);
        }
        else if (block < 75) {
            name = "t";
            return createTBlock(blockA, blockB, blockC, blockD, name);
        }
        else if (block < 90) {
            name = "z";
            return createZBlock(blockA, blockB, blockC, blockD, name);
        }
        else {
            name = "i";
            return createIBlock(blockA, blockB, blockC, blockD, name);
        }
    }

    private static void shiftBlockRight(Rectangle block) {
        if (block.getX() + moveSpeed <= xMax - blockSize) {
            block.setX(block.getX() + moveSpeed);
        }
    }

    private static void shiftBlockUp(Rectangle block) {
        if (block.getY() - moveSpeed > 0) {
            block.setY(block.getY() - moveSpeed);
        }
    }

    public static void shiftBlockDown(Rectangle block) {
        if (block.getY() + moveSpeed < yMax) {
            block.setY(block.getY() + moveSpeed);
        }
    }

    //If can't shift down
    public static void shiftBlockDown(Form form, Pane group, int score, int totalLines, Form nextObj, Form object) {
        if (form.blockA.getY() == yMax - blockSize || form.blockB.getY() == yMax - blockSize || form.blockC.getY() == yMax - blockSize || form.blockD.getY() == yMax - blockSize || moveBlock(form)) {
            playGrid[(int) form.blockA.getX() / blockSize][(int) form.blockA.getY() / blockSize] = 1;
            playGrid[(int) form.blockB.getX() / blockSize][(int) form.blockB.getY() / blockSize] = 1;
            playGrid[(int) form.blockC.getX() / blockSize][(int) form.blockC.getY() / blockSize] = 1;
            playGrid[(int) form.blockD.getX() / blockSize][(int) form.blockD.getY() / blockSize] = 1;
            deleteCompleteRows(group, score, totalLines);

            Form a = nextObj;
            nextObj = Controller.makeRect();
            object = a;
            group.getChildren().addAll(a.blockA, a.blockB, a.blockC, a.blockD);
            tetris.moveOnKeyPress(a);
        }

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

    private static void shiftBlockLeft(Rectangle block) {
        if (block.getX() - moveSpeed > 0) {
            block.setY(block.getY() - moveSpeed);
        }
    }

    private static boolean checkBlock(Rectangle block, int x, int y) {
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

    private static void deleteCompleteRows(Pane pane, int score, int totalLines) {
        ArrayList<Node> rects = new ArrayList<Node>();
        ArrayList<Integer> lines = new ArrayList<Integer>();
        ArrayList<Node> newRects = new ArrayList<Node>();
        int filledSquares = 0;

        //check which lines are full
        for (int i = 0; i < playGrid[0].length; i++) {
            for (int j = 0; j < playGrid.length; j++) {
                if (playGrid[j][i] == 1) {
                    filledSquares++;
                }
            }
            if (filledSquares == playGrid.length) {
                lines.add(i);
            }
            filledSquares = 0;
        }

        //deleting block from row
        if (lines.size() > 0) {
            do {
                for (Node node: pane.getChildren()) {
                    if (node instanceof Rectangle) {
                        rects.add(node);
                    }
                }
                score += 50;
                totalLines++;

                //deleting block on row
                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() == lines.get(0 * blockSize)) {
                        playGrid[(int) a.getX() / blockSize][(int) a.getY() / blockSize] = 0;
                        pane.getChildren().remove(node);
                    }
                    else {
                        newRects.add(node);
                    }
                }

                for (Node node: newRects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() == lines.get(0 * blockSize)) {
                        playGrid[(int) a.getX() / blockSize][(int) a.getY() / blockSize] = 0;
                        pane.getChildren().remove(node);
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
            }
            while (lines.size() > 0);
        }
    }

    public static Form createJBlock(Rectangle blockA, Rectangle blockB, Rectangle blockC, Rectangle blockD, String name) {
        blockA.setX(xMax / 2 - blockSize);
        blockB.setX(xMax / 2 - blockSize);
        blockB.setY(blockSize);
        blockC.setX(xMax / 2);
        blockC.setY(blockSize);
        blockD.setX(xMax / 2 + blockSize);
        blockD.setY(blockSize);

        return new Form(blockA, blockB, blockC, blockD, name);
    }

    private static void rotateJBlock(Form form, int f) {
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

    public static Form createLBlock(Rectangle blockA, Rectangle blockB, Rectangle blockC, Rectangle blockD, String name) {
        blockA.setX(xMax / 2 + blockSize);
        blockB.setX(xMax / 2 - blockSize);
        blockB.setY(blockSize);
        blockC.setX(xMax / 2);
        blockC.setY(blockSize);
        blockD.setX(xMax / 2 + blockSize);
        blockD.setY(blockSize);

        return new Form(blockA, blockB, blockC, blockD, name);
    }

    private static void rotateLBlock(Form form, int f) {
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

    public static Form createOBlock(Rectangle blockA, Rectangle blockB, Rectangle blockC, Rectangle blockD, String name) {
        blockA.setX(xMax / 2 - blockSize);
        blockB.setX(xMax / 2);
        blockB.setY(xMax / 2 - blockSize);
        blockC.setX(blockSize);
        blockD.setX(xMax / 2);
        blockD.setY(blockSize);

        return new Form(blockA, blockB, blockC, blockD, name);
    }

    public static Form createSBlock(Rectangle blockA, Rectangle blockB, Rectangle blockC, Rectangle blockD, String name) {
        blockA.setX(xMax / 2 + blockSize);
        blockB.setX(xMax / 2);
        blockB.setY(xMax / 2);
        blockC.setY(blockSize);
        blockD.setX(xMax / 2 - blockSize);
        blockD.setY(blockSize);

        return new Form(blockA, blockB, blockC, blockD, name);
    }

    private static void rotateSBlock(Form form, int f) {
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

    public static Form createTBlock(Rectangle blockA, Rectangle blockB, Rectangle blockC, Rectangle blockD, String name) {
        blockA.setX(xMax / 2 - blockSize);
        blockB.setX(xMax / 2);
        blockB.setY(xMax / 2);
        blockC.setY(blockSize);
        blockD.setX(xMax / 2 + blockSize);
        blockD.setY(blockSize);

        return new Form(blockA, blockB, blockC, blockD, name);
    }

    private static void rotateTBlock(Form form, int f) {
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

    public static Form createZBlock(Rectangle blockA, Rectangle blockB, Rectangle blockC, Rectangle blockD, String name) {
        blockA.setX(xMax / 2 + blockSize);
        blockB.setX(xMax / 2);
        blockB.setY(xMax / 2 + blockSize);
        blockC.setY(blockSize);
        blockD.setX(xMax / 2 + blockSize + blockSize);
        blockD.setY(blockSize);

        return new Form(blockA, blockB, blockC, blockD, name);
    }

    private static void rotateZBlock(Form form, int f) {
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

    public static Form createIBlock(Rectangle blockA, Rectangle blockB, Rectangle blockC, Rectangle blockD, String name) {
        blockA.setX(xMax / 2 - blockSize - blockSize);
        blockB.setX(xMax / 2 - blockSize);
        blockB.setY(xMax / 2);
        blockD.setX(xMax / 2 + blockSize);

        return new Form(blockA, blockB, blockC, blockD, name);
    }

    private static void rotateIBlock(Form form, int f) {
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
