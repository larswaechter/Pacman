package com.larswaechter;

import processing.core.PGraphics;

/**
 * Timer based on frame rate
 */

public class Timer {
    private final int frameCountEnd;
    private int frameCountCurrent;

    /**
     * @param ttl Time to live in seconds
     */
    public Timer(int ttl) {
        this.frameCountCurrent = Game.frameCount;
        this.frameCountEnd = this.frameCountCurrent + Game.framesPerSecond * ttl;
    }

    /**
     * Format seconds to m:ss format;
     *
     * @param seconds Seconds to format
     * @return Formatted seconds
     */
    public static String formatSeconds(int seconds) {
        int minutesFormatted = seconds / 60;
        int secondsFormatted = seconds % 60;

        return minutesFormatted + ":" + (secondsFormatted < 10 ? "0" + secondsFormatted : secondsFormatted);
    }

    public String getTimeLeft() {
        return Timer.formatSeconds(this.getSecondsLeft());
    }

    public boolean tickAndVerify() {
        this.tick();
        return this.isExpired();
    }

    public void tick() {
        this.frameCountCurrent++;
    }

    public boolean isExpired() {
        return this.frameCountCurrent >= this.frameCountEnd;
    }

    public void draw(PGraphics g, int x, int y) {
        g.fill(Utility.colorWhite);
        g.textSize(14);
        g.text("Countdown: " + this.getTimeLeft(), x, y);
    }

    /**
     * Get seconds left of timer
     *
     * @return Seconds left
     */
    private int getSecondsLeft() {
        return  (this.frameCountEnd - this.frameCountCurrent) / Game.framesPerSecond;
    }
}
