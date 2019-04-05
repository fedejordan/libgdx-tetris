package com.federicojordan.tetris.figures;

import com.badlogic.gdx.graphics.Color;
import com.federicojordan.tetris.Square;

import java.util.ArrayList;

public class BarFigure extends Figure {

    public BarFigure(int x, int y, Color color) {
        super();
        this.squares = new ArrayList<Square>();
        squares.add(new Square(0, 0, color));
        squares.add(new Square(0, 1, color));
        squares.add(new Square(0, 2, color));
        squares.add(new Square(0, 3, color));
    }
}