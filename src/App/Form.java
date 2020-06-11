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
        setPieceColor();
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
            case "red":
                color = Color.MAROON;
                break;
            case "green":
                color = Color.OLIVE;
                break;
            case "blue":
                color = Color.SLATEBLUE;
                break;
            case "yellow":
                color = Color.DARKGOLDENROD;
                break;
            case "purple":
                color = Color.DARKVIOLET;
                break;
            case "orange":
                color = Color.DARKORANGE;
                break;
            case "grey":
                color = Color.DARKGRAY;
                break;
        }
    }

    public void setPieceColor() {
        blockA.setFill(color);
        blockB.setFill(color);
        blockC.setFill(color);
        blockD.setFill(color);
    }
}
