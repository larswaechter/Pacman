package com.larswaechter;

class Utility {
    static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * ((max - min))) + min;
    }
}
