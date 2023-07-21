package org.example.Toys;

import org.example.Names;

import java.util.Random;

public abstract class Toy {
    int id;
    String name;
    int quantity;
    int weight;

    public Toy(int id, String name, int quantity, int weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public Toy(int id) {
        this();
    }
    public static String getRandomName(){
        return Names.values()[new Random().nextInt(Names.values().length)].toString();
    }
}
