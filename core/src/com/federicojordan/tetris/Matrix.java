package com.federicojordan.tetris;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Matrix {

    void draw(Batch batch) {

        int horizontalLinesCount = (int) (GameConfiguration.screenHeight() / GameConfiguration.squareLenght());

        Pixmap linePixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        linePixmap.setColor(Color.WHITE);
        linePixmap.fill();
        Texture lineTexture = new Texture(linePixmap);

        for (int i=0;i<GameConfiguration.verticalLinesCount();i++) {
            batch.draw(lineTexture , i*GameConfiguration.squareLenght(), 0, 1, GameConfiguration.screenHeight());
        }
        for (int i=0;i<horizontalLinesCount;i++) {
            batch.draw(lineTexture , 0, i*GameConfiguration.squareLenght(), GameConfiguration.screenWidth(), 1);
        }
    }
}
