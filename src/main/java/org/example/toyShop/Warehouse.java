package org.example.toyShop;

import java.util.ArrayList;

public class Warehouse {
    int lastToyId;
    private final ArrayList<Toy> toys;

    public Warehouse() {
        this.toys = new ArrayList<>();
    }

    /**
     * Добавляет игрушку на склад и присваивает ей ID
     * @param type
     * @param weight
     */
    public void addToy(String type, int weight) {
        toys.add(new Toy(type, weight));
        toys.get(toys.size() - 1).setId(this.getNewID());
    }

    public Toy takeFromWarehouse(String type) {
        for (Toy toy: toys) {
            if (toy.getType().equals(type)) {
                toys.remove(toy);
                return toy;
            }
        }

        return null;
    }

    public int getNewID() {
        this.lastToyId++;
        return this.lastToyId;
    }

    public ArrayList<Toy> getToys() {
        return toys;
    }

    public int getToyQuantity() {
        return toys.size();
    }
}
