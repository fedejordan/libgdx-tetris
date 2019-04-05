package com.federicojordan.tetris.figures;

import com.badlogic.gdx.graphics.Color;
import com.federicojordan.tetris.Square;

import java.util.ArrayList;

public class ReversedZFigure extends Figure {


    public ReversedZFigure(int x, int y, Color color) {
        super();
        this.squares = new ArrayList<Square>();
        squares.add(new Square(x, y+1, color));
        squares.add(new Square(x+1, y, color));
        squares.add(new Square(x+1, y+1, color));
        squares.add(new Square(x+2, y, color));
    }
}
