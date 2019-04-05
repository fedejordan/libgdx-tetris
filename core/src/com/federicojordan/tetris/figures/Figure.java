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
        for (Square square : squares) {
            square.moveDown();
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

    public void moveLeft() {
        for (Square square : squares) {
            square.moveLeft();
        }
    }

    public void moveRight() {
        for (Square square : squares) {
            square.moveRight();
        }
    }

    public boolean isTouchingRight(Square deadSquare) {
        for (Square square1 : squares) {
            if(square1.isTouchingRight(deadSquare)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTouchingLeft(Square deadSquare) {
        for (Square square1 : squares) {
            if(square1.isTouchingLeft(deadSquare)) {
                return true;
            }
        }
        return false;
    }
}
