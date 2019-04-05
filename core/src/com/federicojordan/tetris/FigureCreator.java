package com.federicojordan.tetris;

import com.badlogic.gdx.graphics.Color;
import com.federicojordan.tetris.figures.BarFigure;
import com.federicojordan.tetris.figures.BoxFigure;
import com.federicojordan.tetris.figures.Figure;
import com.federicojordan.tetris.figures.ReversedZFigure;
import com.federicojordan.tetris.figures.TFigure;
import com.federicojordan.tetris.figures.ZFigure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class FigureCreator {

    static Figure createRandom(int x, int y) {
        Random rand = new Random();
        Color color = randomColor();

        int n = rand.nextInt(5);
        return Arrays.asList(new BarFigure(x, y, color),
                new BoxFigure(x, y, color),
                new ReversedZFigure(x, y, color),
                new TFigure(x, y, color),
                new ZFigure(x, y, color)).get(n);
    }

    private static Color randomColor() {
        Random rand = new Random();

        int n = rand.nextInt(5);
        return Arrays.asList(Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.ORANGE).get(n);
    }
}
