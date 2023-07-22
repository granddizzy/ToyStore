package org.example.toyShop;

import org.example.toyShop.toys.Toy;

import java.util.ArrayList;

public class Showcase {

    private final ArrayList<Toy> toys;
    private int lastId;

    public Showcase() {
        this.lastId = 0;
        this.toys = new ArrayList<>();
    }

    public void addToy(Toy toy){
        this.lastId = lastId + 1;
        toy.setId(lastId);
        toys.add(toy);
    }

    public Toy getToy(int toyId) {
        for (Toy toy:  toys) {
            if (toy.getId() == toyId) {
                return toy;
            }
        }

        return null;
    }

    public void delToy(Toy toy){
        toys.remove(toy);
    }

    public ArrayList<Toy> getToys() {
        return toys;
    }

    public int getNewID(){
        return lastId + 1;
    }

    public int getQuantity(Toy toy) {
        int quantity = 0;
        for (Toy tmpToy :toys) {
            if (toy.getClass() == tmpToy.getClass()) {
                quantity++;
            }
        }

        return quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Toy toy: toys) {
            sb.append(toy.toString()).append("\n");
        }

        return sb.toString();
    }
}
