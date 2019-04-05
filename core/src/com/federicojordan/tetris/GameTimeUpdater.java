package com.federicojordan.tetris;

public class GameTimeUpdater {
    private long currentTimeMillis;
    GameTimeUpdaterListener listener;
    private float DELTA = 0.001f;

    public GameTimeUpdater(GameTimeUpdaterListener listener) {
        this.listener = listener;
    }

    void render() {
        long newTimeMillis = System.currentTimeMillis();
        float frameTimeSeconds = (newTimeMillis - currentTimeMillis) / 1000f;
        if(frameTimeSeconds >= DELTA) {
            currentTimeMillis = newTimeMillis;
            listener.shouldUpdate();
        }
    }
}
