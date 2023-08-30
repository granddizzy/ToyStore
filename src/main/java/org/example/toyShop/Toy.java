package org.example.toyShop;

import org.example.toyShop.ToyNames;

import java.util.Random;

public class Toy {
    private int id;
    private String name;
    private int weight;
    private String type;

    public Toy(String type, int weight) {
        this.id = 0;
        this.name = getRandomName();
        this.weight = weight;
        this.type = type;
    }

    public static String getRandomName() {
        return ToyNames.values()[new Random().nextInt(ToyNames.values().length)].toString();
    }

    @Override
    public String toString() {
        return "ID: " + this.id + ", Тип: " + this.type + ", Имя: " + this.name;
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

    public String getType() {
        return type;
    }
}
