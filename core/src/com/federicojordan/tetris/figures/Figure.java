package com.federicojordan.tetris.figures;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.federicojordan.tetris.Square;

import java.util.ArrayList;

public class Figure {
    ArrayList<Square> squares;

    public void draw(Batch batch) {
        for (Square square : squares) {
            square.draw(batch);
        }
    }
}
