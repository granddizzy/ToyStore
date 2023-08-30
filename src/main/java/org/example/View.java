package org.example;

import org.example.toyShop.Shop;

import java.util.Scanner;

import org.example.toyShop.Showcase;
import org.example.toyShop.Toy;

public class View {

    public void showGreeting(Shop shop) {
        System.out.println(shop.name + " приветствет тебя, мой дорогой хозяин!\nНапоминаю, что сегодня для тебя особенный день: в честь детского праздника ты сегодня проводишь розыгрыш игрушек.\nПосмотри, что для этого можно сделать.\n");
    }

    /**
     * Главное меню
     */
    public void showMainMenu() {
        System.out.println("Главное меню: ");
        System.out.println("1. Осмотреть магазин");
        System.out.println("2. Добавить игрушки на склад");
        System.out.println("3. Добавить витрину");
        System.out.println("4. Удалить витрину");
        System.out.println("5. Пополнить витрины со склада");
        System.out.println("6. Провести розыгрыш");
        System.out.println("7. Раздавать игрушки");
        System.out.println("0. Уйти");
    }

    public String input(String text) {
        System.out.print(text);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void errorFullShowcases() {
        showMessage("Витрина переполнена");
    }

    public void errorEmptyShowcase() {
        showMessage("Витрина пуста");
    }

    public void errorNoShowcases() {
        showMessage("Витрин нет");
    }

    public void errorNotEmptyQueue() {
        showMessage("Раздай предыдущие игрушки!");
    }

    public void errorEmptyPrizeLine() {
        System.out.println("Сначала разыгай игрушки!");
    }

    /**
     * Показывает данные по магазину
     * @param shop
     */
    public void shopInspection(Shop shop) {
        System.out.println("Склад: ");
        if (shop.getWarehouse().getToyQuantity() == 0) {
            errorEmptyWarehouse();
        } else {
            for (Toy toy: shop.getWarehouse().getToys()) {
                System.out.println(toy);
            }
        }

        System.out.println();

        if (shop.getNumberOfShoucases() == 0) {
            System.out.println("Нет витрин.");
        } else {
            for (Showcase showcase : shop.getShowcases()) {
                System.out.println(showcase);
            }
        }

        System.out.println();

        System.out.println("Очередь разыграных игрушек: ");
        if (shop.getPrizeLine().size() == 0) {
            System.out.println("Пусто");
        } else {
            for (Toy toy : shop.getPrizeLine()) {
                System.out.println(toy);
            }
        }

        System.out.println();
    }

    public void showMessage(String text) {
        System.out.println(text);
        System.out.println();
    }

    public void errorEmptyWarehouse() {
        showMessage("Склад пуст");
    }
}
