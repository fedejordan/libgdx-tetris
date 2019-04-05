package com.federicojordan.tetris.figures;

import com.badlogic.gdx.graphics.Color;
import com.federicojordan.tetris.Square;

import java.util.ArrayList;

public class ReversedZFigure extends Figure {


    public ReversedZFigure(int x, int y, Color color) {
        super();
        this.squares = new ArrayList<Square>();
        squares.add(new Square(0, 1, color));
        squares.add(new Square(1, 0, color));
        squares.add(new Square(1, 1, color));
        squares.add(new Square(2, 0, color));
    }
}
