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

    public void moveDown() {
        int minX = squares.get(0).getX();
        int minY = squares.get(0).getY();
        int maxX = squares.get(0).getX();
        int maxY = squares.get(0).getY();

        for (Square square : squares) {
            square.moveDown();
            if(square.getX() < minX) { minX = square.getX(); }
            if(square.getY() < minY) { minY = square.getY(); }
            if(square.getX() > maxX) { maxX = square.getX(); }
            if(square.getY() > maxY) { maxY = square.getY(); }
        }

    }

    public ArrayList<Square> getSquares() {
        return squares;
    }

    public boolean isTouchingBottom(Square square) {
        for (Square square1 : squares) {
            if(square1.isTouchingBottom(square)) {
                return true;
            }
        }
        return false;
    }
}
