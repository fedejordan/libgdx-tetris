package com.federicojordan.tetris;

import com.badlogic.gdx.Gdx;

public class GameConfiguration {


    static float screenWidth() {
        return Gdx.app.getGraphics().getWidth();
    }

    static float screenHeight() {
        return Gdx.app.getGraphics().getHeight();
    }

    static int verticalLinesCount() {
        return 20;
    }

    static float squareLenght() {
        return screenWidth()/verticalLinesCount();
    }
}
