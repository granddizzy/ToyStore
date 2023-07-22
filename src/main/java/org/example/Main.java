package org.example;

import org.example.toyShop.ToyShop;
import org.example.toyShop.toys.Constructor;
import org.example.toyShop.toys.Doll;
import org.example.toyShop.toys.Robot;

public class Main {
    public static void main(String[] args) {

        ToyShop toyShop = new ToyShop();

        // добавляем игрушки в магазин
        toyShop.addToy(new Constructor("Конструктор 1", 10));
        toyShop.addToy(new Robot("Робот 1", 20));
        toyShop.addToy(new Doll("Кукла 1", 30));

        // смотрим витрину
        System.out.println(toyShop.getShowcase());

        // провести лотерею
        toyShop.holdALottery();

        // получить призовую игрушку
        System.out.println(toyShop.getAPrizeToy());
    }
}