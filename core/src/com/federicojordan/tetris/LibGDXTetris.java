package com.federicojordan.tetris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.federicojordan.tetris.figures.Figure;

import java.util.ArrayList;


public class LibGDXTetris extends ApplicationAdapter {
	SpriteBatch batch;
	private Matrix matrix;
	private Figure currentFigure;
	private String TAG = "LibGDXTetris";
	private GameTimeUpdater gameTimeUpdater;
	private ArrayList<Square> deadSquares;

	@Override
	public void create () {
		batch = new SpriteBatch();
		matrix = new Matrix();
		gameTimeUpdater = new GameTimeUpdater(new GameTimeUpdaterListener() {
			@Override
			public void shouldUpdate() {
				currentFigure.moveDown();
				checkCurrentFigure();
			}
		});

		createFigure();
		deadSquares = new ArrayList<Square>();
	}

	private void checkCurrentFigure() {
		if(figureIsTouchingADeadSquare() || figureIsTouchingBottom()) {
			addDeadSquaresFromCurrentFigure();
			createFigure();
		}
	}

	private boolean figureIsTouchingBottom() {
		for (Square square : currentFigure.getSquares()) {
			if(square.getY() == 0) {
				return true;
			}
		}
		return false;
	}

	private void createFigure() {
		int x = matrix.numberOfColumns / 2;
		int y = matrix.numberOfRows;
		Gdx.app.log(TAG, "x: " + x + " y: " + y);
		currentFigure = FigureCreator.createRandom(x, y);
	}

	private boolean figureIsTouchingADeadSquare() {
		for (Square deadSquare : deadSquares) {
			if(currentFigure.isTouchingBottom(deadSquare)) {
				return true;
			}
		}
		return false;
	}

	private void addDeadSquaresFromCurrentFigure() {
		for (Square square : currentFigure.getSquares()) {
			deadSquares.add(square);
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameTimeUpdater.render();
		batch.begin();
		matrix.draw(batch);

		drawFigure();
		drawDeadSquares();
		batch.end();
	}

	private void drawDeadSquares() {
		for (Square deadSquare : deadSquares) {
			deadSquare.draw(batch);
		}
	}

	private void drawFigure() {
		currentFigure.draw(batch);
	}


	@Override
	public void dispose () {
		batch.dispose();
	}
}
