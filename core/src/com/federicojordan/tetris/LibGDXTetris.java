package com.federicojordan.tetris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.federicojordan.tetris.figures.BarFigure;
import com.federicojordan.tetris.figures.Figure;
import com.federicojordan.tetris.figures.ReversedZFigure;
import com.federicojordan.tetris.figures.TFigure;
import com.federicojordan.tetris.figures.ZFigure;


public class LibGDXTetris extends ApplicationAdapter {
	SpriteBatch batch;
	private Matrix matrix;
	private Figure figure;

	@Override
	public void create () {
		batch = new SpriteBatch();
		matrix = new Matrix();
		figure = FigureCreator.createRandom();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		matrix.draw(batch);

		drawFigure();
		batch.end();
	}

	private void drawFigure() {
		figure.draw(batch);
	}


	@Override
	public void dispose () {
		batch.dispose();
	}
}
