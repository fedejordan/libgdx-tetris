package com.federicojordan.tetris;

public class GameTimeUpdater {
    private long currentTimeMillis;
    GameTimeUpdaterListener listener;

    public GameTimeUpdater(GameTimeUpdaterListener listener) {
        this.listener = listener;
    }

    void render() {
        long newTimeMillis = System.currentTimeMillis();
        float frameTimeSeconds = (newTimeMillis - currentTimeMillis) / 1000f;
        if(frameTimeSeconds >= GameConfiguration.velocity()) {
            currentTimeMillis = newTimeMillis;
            listener.shouldUpdate();
        }
    }
}
