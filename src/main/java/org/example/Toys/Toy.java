package org.example.Toys;

import org.example.Names;

import java.util.Random;

public abstract class Toy {
    private int id;
    private String name;
    private int weight;

    public Toy(String name, int weight) {
        this.id = 0;
        this.name = name;
        this.weight = weight;
    }

    public static String getRandomName() {
        return Names.values()[new Random().nextInt(Names.values().length)].toString();
    }

    @Override
    public String toString() {
        return "ID: " + this.id + ", Имя: " + this.name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }
}
