package org.example;

import org.example.toyShop.*;
import org.example.toyShop.toys.Toy;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        // создаем магазин
        Shop shop = new Shop("Магазин игрушек");

        // создаем склад
        Stock stock = new Stock();

        // добавляем витрины
        shop.addShowcase(ToyTypes.Constructor, 10);
        shop.addShowcase(ToyTypes.Doll, 10);
        shop.addShowcase(ToyTypes.Robot, 10);

        View view = new View();
        view.showGreeting(shop);

        while (true) {
            view.showMenu();
            String num = view.input("Выберите пункт меню: ");
            int numb = Integer.parseInt(num);
            if (numb == 5) {
                break;
            } else if (numb == 1) {
                view.shopInspection(shop);
            } else if (numb == 2) {
                if (shop.checkFullShowcases()) {
                    view.errorFullShowcase();
                } else {
                    // заполняем витрины
                    for (Showcase showcase : shop.getShowcases()) {
                        while (!showcase.checkFullShowcase()) {
                            showcase.addToy(stock.takeFromWarehouse(showcase.getType()));
                        }
                    }
                    view.showMessage("Витрины заполнены.");
                }
            } else if (numb == 3) {
                if (shop.checkEmptyShowcases()) {
                    view.errorEmptyShowcase();
                } else {
                    if (shop.getPrizeLine().size() != 0) {
                        view.errorNotEmptyQueue();
                    } else {
                        shop.holdALottery();
                        view.showMessage("Розыгрыш проведен.");
                    }
                }
            } else if (numb == 4) {
                if (shop.getPrizeLineSize() == 0) {
                    view.errorEmptyPrizeLine();
                } else {
                    while (shop.getPrizeLineSize() > 0) {
                        // берем игрушку
                        Toy toy = shop.getAPrizeToy();

                        // выбираем ребенка
                        int index = new Random().nextInt(ChildNames.values().length);
                        ChildNames[] childs = ChildNames.values();
                        ChildNames child = childs[index];

                        // вручаем игрушку
                        view.showMessage(child.toString() + " получил " + toy.getType() + " " + toy.getName());

                        // записать в файл
                    }
                    view.showMessage("Игрушки розданы!");
                }
            }
        }
    }
}