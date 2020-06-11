package App;

import javafx.scene.shape.*;

public class Controller {

    public static final int moveSpeed = Tetris.moveSpeed;
    public static final int blockSize = Tetris.blockSize;
    public static int xMax = Tetris.xMax;
    public static int yMax = Tetris.yMax;
    public static int [][] playGrid = Tetris.playGrid;

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

        
    }
}
