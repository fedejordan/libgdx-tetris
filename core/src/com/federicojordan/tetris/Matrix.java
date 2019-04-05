package com.federicojordan.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Matrix {

    private final Texture lineTexture;
    int numberOfColumns = 0;
    int numberOfRows = 0;

    public Matrix() {
        int horizontalLinesCount = (int) (GameConfiguration.screenHeight() / GameConfiguration.squareLenght());

        Pixmap linePixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        linePixmap.setColor(Color.WHITE);
        linePixmap.fill();
        lineTexture = new Texture(linePixmap);

        numberOfColumns = GameConfiguration.verticalLinesCount();
        numberOfRows = horizontalLinesCount;
    }

    void draw(Batch batch) {


        for (int i=0;i<numberOfColumns;i++) {
            batch.draw(lineTexture , i*GameConfiguration.squareLenght(), 0, 1, GameConfiguration.screenHeight());
        }
        for (int i=0;i<numberOfRows;i++) {
            batch.draw(lineTexture , 0, i*GameConfiguration.squareLenght(), GameConfiguration.screenWidth(), 1);
        }
    }
}
