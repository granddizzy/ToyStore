package org.example.toyShop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Shop {
    private Queue<Toy> prizeLine;
    public final String name;
    private ArrayList<Showcase> showcases;
    private Warehouse warehouse;
    private int lastShowcaseId;

    public Shop(String name) {
        prizeLine = new LinkedList<>();
        this.name = name;
        showcases = new ArrayList<>();
        warehouse = new Warehouse();
        lastShowcaseId = 0;
    }

    public void addShowcase(String type, int size) {
        lastShowcaseId++;
        showcases.add(new Showcase(lastShowcaseId, type, size));
    }

    public Showcase getShowcaseById(int id) {
        for (Showcase sc: showcases) {
            if (sc.getId() == id) return sc;
        }
        return null;
    }

    public boolean delShowcase(Showcase showcase) {
        if (showcase.checkEmptyShowcase()) {
            showcases.remove(showcase);
            return true;
        }

        return false;
    }

    public int getNumberOfShoucases() {
        return showcases.size();
    }

    public Showcase getShowcaseByIndex(int index) {
        return showcases.get(index);
    }

    public ArrayList<Showcase> getShowcases() {
        return showcases;
    }

    public boolean checkFullShowcases() {
        for (Showcase showcase : showcases) {
            if (showcase.getToyQuantity() != showcase.getSize()) return false;
        }
        return true;
    }

    public boolean checkEmptyShowcases() {
        for (Showcase showcase : showcases) {
            if (showcase.getToyQuantity() > 0) return false;
        }
        return true;
    }

    /**
     * Проводит розыгрыш игрушек и добавляет в очередь на выдачу
     */
    public void holdALottery() {
        // создаем колесо
        WheelFortune wheelOfFortune = new WheelFortune();

        // добавляем шары с id игрушек из каждой витрины и шансом выпадения
        for (Showcase showcase : showcases) {
            for (Toy toy : showcase.getToys()) {
                wheelOfFortune.addNubmer(toy.getId(), toy.getWeight());
            }
        }

        // крутим колесо пока есть шары
        while (wheelOfFortune.getNumberOfBalls() > 0) {
            int prizeToyId = wheelOfFortune.run();

            // сектор ПРИЗ на барабане ;)
            prizeLine.add(getToyById(prizeToyId));
        }
    }

    public Toy getAPrizeToy() {
        Toy toy = prizeLine.poll();
        for (Showcase showcase : showcases) {
            if (showcase.contains(toy)) showcase.delToy(toy);
        }

        // записать в файл

        return toy;
    }

    public Queue<Toy> getPrizeLine() {
        return prizeLine;
    }

    public int getPrizeLineSize() {
        return prizeLine.size();
    }

    public Toy getToyById(int id) {
        for (Showcase showcase : showcases) {
            for (Toy toy : showcase.getToys()) {
                if (toy.getId() == id) return toy;
            }
        }

        return null;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }
}
