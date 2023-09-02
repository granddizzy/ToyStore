package org.example.toyShop;

import java.util.ArrayList;

/**
 * Склад для хранения игрушек
 */
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
    public void add(String type, int weight) {
        toys.add(new Toy(type, weight));
        toys.get(toys.size() - 1).setId(this.getNewID());
    }

    public void add(Toy toy) {
        toys.add(toy);
        if (toy.getId()> lastToyId) lastToyId = toy.getId();
    }

    /**
     * Выдает игрушку заданного типа со склада если она есть
     * @param type
     * @return
     */
    public Toy takeFromWarehouse(String type) {
        for (Toy toy: toys) {
            if (toy.getType().equals(type)) {
                toys.remove(toy);
                return toy;
            }
        }

        return null;
    }

    /**
     * Получает следующий идентификатор для игрушки
     * @return
     */
    public int getNewID() {
        this.lastToyId++;
        return this.lastToyId;
    }

    /**
     * Возвращает игрушки на складе
     * @return
     */
    public ArrayList<Toy> getToys() {
        return toys;
    }

    /**
     * Вовзращает количество игрушек на складе
     * @return
     */
    public int getToyQuantity() {
        return toys.size();
    }
}
