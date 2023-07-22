package org.example;

import java.util.ArrayList;
import java.util.Random;

public class WheelFortune {
    ArrayList<BallWheelOfFortune> numbers = new ArrayList<>();

    public void addNubmer(int number, int weight) {
        numbers.add(new BallWheelOfFortune(number, weight));
    }

    public static class BallWheelOfFortune {
        public final int number;

        public final int weight;

        public BallWheelOfFortune(int number, int weight) {
            this.number = number;
            this.weight = weight;
        }
    }

    public int run () {
        int rand = new Random().nextInt(numbers.size());
        return numbers.get(rand).number;
    }
}
