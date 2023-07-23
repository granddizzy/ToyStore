package org.example.toyShop;

import org.example.toyShop.toys.Toy;

import java.util.LinkedList;
import java.util.Queue;

public class ToyShop {
    private Queue<Toy> prizeLine;
    private Showcase showcase;

    public ToyShop() {
        prizeLine = new LinkedList<>();
        showcase = new Showcase();
    }

    public void addToy(Toy toy) {
        showcase.addToy(toy);
    }

    public Showcase getShowcase() {
        return showcase;
    }

    /**
     * Проводит розыгрыш игрушек и добавляет в очередь на выдачу
     */
    public void holdALottery() {
        // создаем колесо
        WheelFortune wheelOfFortune = new WheelFortune();

        // добавляем шары
        for (Toy toy : showcase.getToys()) {
            wheelOfFortune.addNubmer(toy.getId(), toy.getWeight());
        }

        // крутим колесо пока есть шары
        while (wheelOfFortune.getNumberOfBalls() > 0) {
            int prizeToyId = wheelOfFortune.run();

            // сектор ПРИЗ на барабане ;)
            prizeLine.add(showcase.getToy(prizeToyId));
        }
    }

    public Toy getAPrizeToy() {
        Toy toy = prizeLine.poll();
        showcase.delToy(toy);

        // записать в файл

        return toy;
    }
}
