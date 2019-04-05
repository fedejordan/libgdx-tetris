package com.federicojordan.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Square {

    private int x;
    private int y;
    private Texture texture;
    private String TAG = "Square";
    private Color color;

    public Square(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;

        Pixmap squarePixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        squarePixmap.setColor(color);
        squarePixmap.fill();
        this.texture = new Texture(squarePixmap);
    }

    public void draw(Batch batch) {
        float squareLength = GameConfiguration.screenWidth()/GameConfiguration.verticalLinesCount();

        batch.draw(texture, x*squareLength, y*squareLength, squareLength, squareLength);
    }

    public void moveDown() {
        y-=1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isTouching(Square square) {
        boolean isTouchingLeft =    square.getX() == x - 1 && square.getY() == y;
        boolean isTouchingTop =     square.getY() == y + 1 && square.getX() == x;
        boolean isTouchingRight =   square.getX() == x + 1 && square.getY() == y;
        boolean isTouchingBottom =  square.getY() == y - 1 && square.getX() == x;
        return isTouchingLeft || isTouchingTop || isTouchingRight || isTouchingBottom;
    }

    public boolean isTouchingBottom(Square square) {
        return square.getY() == y - 1 && square.getX() == x;
    }

    public void moveRight() {
        x+=1;
    }

    public void moveLeft() {
        x-=1;
    }

    public boolean isTouchingRight(Square square) {
        return square.getX() == x + 1 && square.getY() == y;
    }

    public boolean isTouchingLeft(Square square) {
        return square.getX() == x - 1 && square.getY() == y;
    }

    public Color getColor() {
        return color;
    }
}
