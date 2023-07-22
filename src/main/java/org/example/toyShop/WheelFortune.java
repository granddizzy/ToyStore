package org.example.toyShop;

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
        int ballNumber =  numbers.get(rand).number;

        // удаляем шар из колеса
        numbers.remove(numbers.get(rand));

        return ballNumber;
    }

    public int getCountBalls() {
        return numbers.size();
    }
}
