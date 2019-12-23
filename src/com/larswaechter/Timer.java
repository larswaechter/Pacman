package com.larswaechter;

/**
 * Timer based on frame rate
 */
public class Timer {
    private int frameRateEnd;
    private int frameRateCurrent;

    public Timer(int ttl) {
        this.frameRateCurrent = Game.frameCount;
        this.frameRateEnd = this.frameRateCurrent + ((int) Math.pow(Game.framesPerSecond, 2) * ttl);
    }

    public boolean tickAndVerify() {
        this.tick();
        return this.isExpired();
    }

    private boolean isExpired() {
        return this.frameRateCurrent >= this.frameRateEnd;
    }

    private void tick() {
        this.frameRateCurrent += Game.framesPerSecond;
    }
}
