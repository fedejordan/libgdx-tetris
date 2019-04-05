package com.federicojordan.tetris.figures;

import com.badlogic.gdx.graphics.Color;
import com.federicojordan.tetris.Square;

import java.util.ArrayList;

public class BarFigure extends Figure {

    private boolean isHorizontal = false;

    public BarFigure(int x, int y, Color color) {
        super();
        this.squares = new ArrayList<Square>();
        squares.add(new Square(x, y, color));
        squares.add(new Square(x, y+1, color));
        squares.add(new Square(x, y+2, color));
        squares.add(new Square(x, y+3, color));
    }

    @Override
    public void rotate() {
        int x = squares.get(0).getX();
        int y = squares.get(0).getY();
        Color color = squares.get(0).getColor();

        squares = new ArrayList();

        if(isHorizontal) {
            squares.add(new Square(x, y, color));
            squares.add(new Square(x, y+1, color));
            squares.add(new Square(x, y+2, color));
            squares.add(new Square(x, y+3, color));
        } else {
            squares.add(new Square(x, y, color));
            squares.add(new Square(x+1, y, color));
            squares.add(new Square(x+2, y, color));
            squares.add(new Square(x+3, y, color));
        }
        isHorizontal = !isHorizontal;
    }
}
