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

    public static Form createTBlock(Rectangle blockA, Rectangle blockB, Rectangle blockC, Rectangle blockD, String name) {
        blockA.setX(xMax / 2 - blockSize);
        blockB.setX(xMax / 2);
        blockB.setY(xMax / 2);
        blockC.setY(blockSize);
        blockD.setX(xMax / 2 + blockSize);
        blockD.setY(blockSize);

        return new Form(blockA, blockB, blockC, blockD, name);
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

    public static Form createIBlock(Rectangle blockA, Rectangle blockB, Rectangle blockC, Rectangle blockD, String name) {
        blockA.setX(xMax / 2 - blockSize - blockSize);
        blockB.setX(xMax / 2 - blockSize);
        blockB.setY(xMax / 2);
        blockD.setX(xMax / 2 + blockSize);

        return new Form(blockA, blockB, blockC, blockD, name);
    }


}
