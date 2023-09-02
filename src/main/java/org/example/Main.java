package org.example;

import org.example.toyShop.*;
import org.example.toyShop.Toy;

import java.io.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        // создаем магазин
        Shop shop = new Shop("Магазин игрушек");
        shop.init();

        View view = new View();
        view.showGreeting(shop);

        label:
        while (true) {
            view.showMainMenu();
            String num = view.input("Выберите пункт меню: ");
            switch (num) {
                case "0":
                    shop.save();
                    break label;
                case "1":
                    view.shopInspection(shop);
                    break;
                case "2": {
                    String toyType = view.input("Введите тип игрушки:");
                    if (toyType.isEmpty()) continue;
                    String numberOfToys;
                    do {
                        numberOfToys = view.input("Введите количество игрушек:");
                    } while (!checkIsDigit(numberOfToys) || Integer.parseInt(numberOfToys) <= 0);
                    String weightOfToys;
                    do {
                        weightOfToys = view.input("Введите вес выпадения игрушки:");
                    } while (!checkIsDigit(weightOfToys));
                    for (int i = 0; i < Integer.parseInt(numberOfToys); i++) {
                        shop.getWarehouse().add(toyType, Integer.parseInt(weightOfToys));
                    }
                    shop.save();
                    break;
                }
                case "3": {
                    String toyType = view.input("Введите тип игрушек на витрине:");
                    if (toyType.isEmpty()) continue;
                    String showcaseSize;
                    do {
                        showcaseSize = view.input("Введите размер витрины:");
                    } while (!checkIsDigit(showcaseSize) || Integer.parseInt(showcaseSize) <= 0);
                    shop.addShowcase(toyType, Integer.parseInt(showcaseSize));
                    shop.save();
                    break;
                }
                case "4":
                    String idShowcase;
                    do {
                        idShowcase = view.input("Введите ID витрины для удаления:");
                    } while (!checkIsDigit(idShowcase));
                    Showcase sc = shop.getShowcaseById(Integer.parseInt(idShowcase));
                    if (sc == null) {
                        view.showMessage("Нет витрины с таким ID");
                    } else {
                        if (shop.delShowcase(sc)) {
                            view.showMessage("Витрина c ID: " + idShowcase + " удалена");
                            shop.save();
                        }
                        else view.showMessage("Не могу удалить витрину c ID: " + idShowcase + ". На ней есть игрушки.");
                    }
                    break;
                case "5":
                    if (shop.getNumberOfShoucases() == 0) {
                        view.errorNoShowcases();
                    } else if (shop.checkFullShowcases()) {
                        view.errorFullShowcases();
                    } else {
                        // заполняем витрины
                        for (Showcase showcase : shop.getShowcases()) {
                            while (!showcase.checkFullShowcase()) {
                                Toy newToy = shop.getWarehouse().takeFromWarehouse(showcase.getType());
                                if (newToy == null) {
                                    view.showMessage("На складе не хватает игрушек типа:" + showcase.getType());
                                    break;
                                }
                                showcase.addToy(newToy);
                            }
                        }
                        for (Showcase showcase : shop.getShowcases()) {
                            view.showMessage("Витрина ID:" + showcase.getId() + " с игрушками типа:" + showcase.getType() + " заполнена на " + Math.round((double) showcase.getToyQuantity() / showcase.getSize() * 100) + "%");
                        }
                        shop.save();
                    }
                    break;
                case "6":
                    if (shop.getNumberOfShoucases() == 0) {
                        view.errorNoShowcases();
                    } else if (shop.checkEmptyShowcases()) {
                        view.errorEmptyShowcase();
                    } else {
                        if (shop.getPrizeLine().size() != 0) {
                            view.errorNotEmptyQueue();
                        } else {
                            shop.holdALottery();
                            view.showMessage("Розыгрыш проведен.");
                            shop.save();
                        }
                    }
                    break;
                case "7":
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
                            view.showMessage(child.toString() + " получил(а) " + toy.getType() + " " + toy.getName());

                            // записать в файл
                            String file_path = "log.txt";
                            saveInLog(file_path, toy, child);
                        }
                        shop.save();
                        view.showMessage("Игрушки розданы!");
                    }
                    break;
            }
        }
    }

    /**
     * Проверка является ли введенная строка числом
     * @param number
     * @return
     */
    private static boolean checkIsDigit(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Записывает выданную ребенку игрушку в файл
     * @param file_path
     * @param toy
     * @param child
     */
    private static void saveInLog(String file_path, Toy toy, ChildNames child) {
        File file = new File(file_path);
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            br.write(child.toString() + " получил(а) " + toy.getType() + " " + toy.getName() + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}