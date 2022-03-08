package com.company;

import java.util.Random;

public class Event{
    public int getRandomNumber(int min, int max, int block1, int block2) {
        Random rand = new Random();
        int randomNum;

        do {
            randomNum = rand.nextInt((max - min) + 1) + min;
        } while (randomNum == block1 || randomNum == block2);

        return randomNum;
    }
}
