package com.federicojordan.tetris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.federicojordan.tetris.figures.Figure;

import java.util.ArrayList;
import java.util.Random;


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
		initTouchHandling();
	}

	private void initTouchHandling() {
		Gdx.input.setInputProcessor(new InputAdapter(){

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {

				if(screenY < GameConfiguration.screenHeight() / 4) {
					tryToRotate();
				} else if(screenY > GameConfiguration.screenHeight() / 4 * 3) {
					tryToMoveDown();
				} else if(screenX < GameConfiguration.screenWidth() / 2) {
					tryToMoveLeft();
				} else {
					tryToMoveRight();
				}

				return true;
			}

		});
	}

	private void tryToMoveDown() {
		boolean shouldNotKeepMoving = figureIsTouchingADeadSquareAtBottom() || figureIsTouchingBottom();
		if(shouldNotKeepMoving == false) {
			currentFigure.moveDown();
		}
	}

	private void tryToRotate() {
		Gdx.app.log(TAG, "try to rotate");
		currentFigure.rotate();
	}

	private void tryToMoveRight() {
		if(canMoveFigureToRight()) {
			currentFigure.moveRight();
		}
	}

	private boolean canMoveFigureToRight() {
		boolean shouldNotKeepMoving = figureIsTouchingADeadSquareAtRight() || figureIsTouchingRightLimit();
		return shouldNotKeepMoving == false;
	}

	private boolean figureIsTouchingRightLimit() {
		for (Square square : currentFigure.getSquares()) {
			if(square.getX() == matrix.numberOfColumns-1) {
				return true;
			}
		}
		return false;
	}

	private boolean figureIsTouchingADeadSquareAtRight() {
		for (Square deadSquare : deadSquares) {
			if(currentFigure.isTouchingRight(deadSquare)) {
				return true;
			}
		}
		return false;
	}

	private void tryToMoveLeft() {
		if(canMoveFigureToLeft()) {
			currentFigure.moveLeft();
		}
	}

	private boolean canMoveFigureToLeft() {
		boolean shouldNotKeepMoving = figureIsTouchingADeadSquareAtLeft() || figureIsTouchingLeftLimit();
		return shouldNotKeepMoving == false;
	}

	private boolean figureIsTouchingLeftLimit() {
		for (Square square : currentFigure.getSquares()) {
			if(square.getX() == 0) {
				return true;
			}
		}
		return false;
	}

	private boolean figureIsTouchingADeadSquareAtLeft() {
		for (Square deadSquare : deadSquares) {
			if(currentFigure.isTouchingLeft(deadSquare)) {
				return true;
			}
		}
		return false;
	}

	private void checkCurrentFigure() {
		boolean shouldNotKeepMoving = figureIsTouchingADeadSquareAtBottom() || figureIsTouchingBottom();
		if(shouldNotKeepMoving) {
			addDeadSquaresFromCurrentFigure();
			tryToDeleteCompletedLine();
			checkFinishedGame();
			createFigure();
		}
	}

	private void checkFinishedGame() {
		for(Square deadSquare: deadSquares) {
			if(deadSquare.getY() == matrix.numberOfRows-1 && deadSquare.getX() == matrix.numberOfColumns / 2 -1) {
				deadSquares = new ArrayList();
			}
		}
	}

	private void tryToDeleteCompletedLine() {
		boolean aLineWasDeleted = false;
		for(int i=0;i<matrix.numberOfRows;i++) {
			ArrayList<Square> selectedSquares = new ArrayList();
			for(int j=0;j<matrix.numberOfColumns;j++) {
				for(Square deadSquare: deadSquares) {

					if(deadSquare.getY() == i && deadSquare.getX() == j) {
						//Gdx.app.log(TAG, "Found square x: " + j + "y: " + i);
						selectedSquares.add(deadSquare);
					}
				}
			}

			if(selectedSquares.size() == matrix.numberOfColumns) {
				Gdx.app.log(TAG, "There is a line on y = " + i);
				deadSquares.removeAll(selectedSquares);

				// Move the top ones to bottom
				for(Square moveToSquare: deadSquares) {
					if(moveToSquare.getY() > i) {
						moveToSquare.moveDown();
					}
				}

				aLineWasDeleted = true;
			}
		}

		if(aLineWasDeleted) {
			tryToDeleteCompletedLine();
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
		int x = matrix.numberOfColumns / 2 -1;
		int y = matrix.numberOfRows;
		currentFigure = FigureCreator.createRandom(x, y);
	}

	private boolean figureIsTouchingADeadSquareAtBottom() {
		for (Square deadSquare : deadSquares) {
			if(currentFigure.isTouchingBottom(deadSquare)) {
				return true;
			}
		}
		return false;
	}

	private void addDeadSquaresFromCurrentFigure() {
		for (Square square : currentFigure.getSquares()) {
			if(GameConfiguration.shouldSetGrayColorWhenDead()) {
				square.setColor(Color.GRAY);
			}
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
