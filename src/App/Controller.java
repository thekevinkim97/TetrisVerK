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
                    break;
                }
            case "l":
                if ((f == 1 && checkBlock(blockA, 1, -1) && checkBlock(blockB, 2, 2) && checkBlock(blockC, 1, 1)) || (f == 2 && checkBlock(blockA, -1, -1) && checkBlock(blockB, 2, -2) && checkBlock(blockC, 1, -1)) || (f == 3 && checkBlock(blockA, -1, 1) && checkBlock(blockB, -2, -2) && checkBlock(blockC, -1, -1)) || (f == 4 && checkBlock(blockA, 1, 1) && checkBlock(blockB, -2, 2) && checkBlock(blockC, -1, 1))) {
                    rotateLBlock(form, f);
                    break;
                }
            case "o":
                break;
        }
    }

    public static void moveDown (Form form) {

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

    private static void shiftBlockDown(Rectangle block) {
        if (block.getY() + moveSpeed < yMax) {
            block.setY(block.getY() + moveSpeed);
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

    private static void rotateOBlock(Form form, int f) {

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

    }

    public static Form createIBlock(Rectangle blockA, Rectangle blockB, Rectangle blockC, Rectangle blockD, String name) {
        blockA.setX(xMax / 2 - blockSize - blockSize);
        blockB.setX(xMax / 2 - blockSize);
        blockB.setY(xMax / 2);
        blockD.setX(xMax / 2 + blockSize);

        return new Form(blockA, blockB, blockC, blockD, name);
    }

    private static void rotateIBlock(Form form, int f) {

    }

}
