package org.example;

import org.example.Toys.Constructor;
import org.example.Toys.Doll;
import org.example.Toys.Robot;
import org.example.Toys.Toy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        Queue<Toy> prizeLine = new LinkedList<>();

        Showcase showcase = new Showcase();

        showcase.addToy(new Constructor("Конструктор 1", 10));
        showcase.addToy(new Robot("Робот 1", 20));
        showcase.addToy(new Doll("Кукла 1", 30));

        System.out.println(showcase);

        // создаем колесо
        WheelFortune wheelOfFortune = new WheelFortune();

        // добавляем шары
        for (Toy toy : showcase.getToys()) {
            wheelOfFortune.addNubmer(toy.getId(), toy.getWeight());
        }

        // крутим колесо ... сектор ПРИЗ на барабане ;)
        int prizeToyId = wheelOfFortune.run();

        //добавляем в очередь на получение
        prizeLine.add(showcase.getToy(prizeToyId));


    }
}