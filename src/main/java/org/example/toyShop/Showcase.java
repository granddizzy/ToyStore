package org.example.toyShop;

import java.util.ArrayList;

/**
 * Витрина с игрушками
 */
public class Showcase {
    private final ArrayList<Toy> toys;
    private final int size;
    private int id;

    private String type;

    public Showcase(int id, String type, int size) {
        this.toys = new ArrayList<>();
        this.size = size;
        this.type = type;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * Добавляет игрушку на витрину
     *
     * @param toy
     */
    public void addToy(Toy toy) {
        if (toy.getId() != 0) {
            toys.add(toy);
        }
    }

    public Toy getToyById(int toyId) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                return toy;
            }
        }

        return null;
    }

    public Toy getToyByIndex(int index) {
        return toys.get(index);
    }

    /**
     * Удаляет игрушку с витрины
     *
     * @param toy
     */
    public void delToy(Toy toy) {
        toys.remove(toy);
    }

    /**
     * Возвращает игрушки на витрине
     *
     * @return
     */
    public ArrayList<Toy> getToys() {
        return toys;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Витрина ID:").append(id).append(" Тип:").append(this.type).append("\n");
        if (toys.size() > 0) {
            for (Toy toy : toys) {
                sb.append(toy.toString()).append("\n");
            }
        } else {
            sb.append("Пусто");
        }

        return sb.toString();
    }

    /**
     * Возвращает количество игрушек на витрине
     *
     * @return
     */
    public int getToyQuantity() {
        return toys.size();
    }

    /**
     * Возвращает размер витрины
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * Возвращает тип игрушек на витрине
     *
     * @return
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (toys.size() == 0) {
            this.type = type;
        }
    }

    /**
     * Проверяет есть ли такая игрушка на витрине
     *
     * @param toy
     * @return
     */
    public boolean contains(Toy toy) {
        for (Toy tmpToy : toys) {
            if (tmpToy.equals(toy)) return true;
        }
        return false;
    }

    /**
     * Проверяет заполнена ли витрина полностью
     *
     * @return
     */
    public boolean checkFullShowcase() {
        return this.getToyQuantity() == this.getSize();
    }

    /**
     * Проверяет пуста ли витрина
     *
     * @return
     */
    public boolean checkEmptyShowcase() {
        return this.getToyQuantity() <= 0;
    }

    public String getBaseString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getId()).append(";").append(this.getType()).append(";").append(this.getSize());

        return sb.toString();
    }
}
