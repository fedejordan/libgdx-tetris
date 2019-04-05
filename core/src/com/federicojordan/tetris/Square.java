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

    public Square(int x, int y, Color color) {
        this.x = x;
        this.y = y;

        Pixmap squarePixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        squarePixmap.setColor(color);
        squarePixmap.fill();
        this.texture = new Texture(squarePixmap);
    }

    public void draw(Batch batch) {
        float squareLength = GameConfiguration.screenWidth()/GameConfiguration.verticalLinesCount();

        batch.draw(texture, x*squareLength, y*squareLength, squareLength, squareLength);
    }
}
