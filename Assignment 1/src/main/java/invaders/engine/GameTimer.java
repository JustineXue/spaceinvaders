package invaders.engine;

import javafx.animation.AnimationTimer;
public class GameTimer extends AnimationTimer{

    private long startTime;
    private long elapsedTime;
    public GameTimer(){
        this.startTime = -1;
        this.elapsedTime = 0;
    }
    @Override
    public void handle(long now) {
        if (startTime == 0) {
            // Start the timer when the first frame is rendered
            startTime = now;
        }

        // Calculate the elapsed time (excluding startup time)
        elapsedTime = now - startTime;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public double getElapsedTimeMillis() {
        return elapsedTime / 1_000_000.0; // Convert nanoseconds to milliseconds
    }
}