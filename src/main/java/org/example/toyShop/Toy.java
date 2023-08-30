package org.example.toyShop;

import org.example.toyShop.ToyNames;

import java.util.Random;

/**
 * Игрушка
 */
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

    /**
     * Получает случайное имя для игрушки
     * @return
     */
    public static String getRandomName() {
        return ToyNames.values()[new Random().nextInt(ToyNames.values().length)].toString();
    }

    @Override
    public String toString() {
        return "ID: " + this.id + ", Тип: " + this.type + ", Имя: " + this.name;
    }

    /**
     * Устанавливает идентификатор для игрушки
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Возвращает идентификатор игрушки
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Вовзращает вес игрушки при розыгрыше
     * @return
     */
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Возвращает имя игрушки
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает тип игрушки
     * @return
     */
    public String getType() {
        return type;
    }
}
