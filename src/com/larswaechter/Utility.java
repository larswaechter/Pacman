package com.larswaechter;

public class Utility {
    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * ((max - min))) + min;
    }

    // Colors
    public static int colorYellow = 0xFFFFFF00;
    public static int colorWhite = 0xFFFFFFFF;
    public static int colorBlue = 0xFF4c8dd6;
    public static int colorCyan = 0xFF00FFFF;
    public static int colorOrange = 0xFFff7142;
    public static int colorGreen = 0xFFc6e48b;

    // Ghost colors
    public static int colorGhostRed = 0xFFFF0000;
    public static int colorGhostPink = 0xFFFFB8FF;
    public static int colorGhostCyan = 0xFF00FFFF;
    public static int colorGhostOrange = 0xFFFFB852;
}
