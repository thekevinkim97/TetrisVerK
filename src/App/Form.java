package App;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Form {
    Rectangle blockA;
    Rectangle blockB;
    Rectangle blockC;
    Rectangle blockD;
    Color color;

    private String name;
    public int form = 1;

    public Form(Rectangle blockA, Rectangle blockB, Rectangle blockC, Rectangle blockD) {
        this.blockA = blockA;
        this.blockB = blockB;
        this.blockC = blockC;
        this.blockD = blockD;
    }

    public Form(Rectangle blockA, Rectangle blockB, Rectangle blockC, Rectangle blockD, String name) {
        this.blockA = blockA;
        this.blockB = blockB;
        this.blockC = blockC;
        this.blockD = blockD;
        this.name = name;

        establishPieceColor(name);
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void changeForm() {
        if (form != 4)
            form++;
        else
            form = 1;
    }

    public void establishPieceColor(String name) {
        switch (name) {
            case "j":
                color = Color.MAROON;
                break;
            case "l":
                color = Color.OLIVE;
                break;
            case "o":
                color = Color.SLATEBLUE;
                break;
            case "s":
                color = Color.BROWN;
                break;
            case "t":
                color = Color.DARKVIOLET;
                break;
            case "z":
                color = Color.DARKORANGE;
                break;
            case "i":
                color = Color.DARKGRAY;
                break;
        }

        setPieceColor(color);
    }

    public void setPieceColor(Color color) {
        blockA.setFill(color);
        blockB.setFill(color);
        blockC.setFill(color);
        blockD.setFill(color);
    }
}
