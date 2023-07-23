package org.example;

import org.example.toyShop.Shop;
import org.example.toyShop.Showcase;
import org.example.toyShop.ShowcaseTypes;
import org.example.toyShop.Stock;
import org.example.toyShop.toys.Toy;

public class Main {
    public static void main(String[] args) {

        // создаем магазин
        Shop shop = new Shop("Магазин игрушек");

        // создаем склад
        Stock stock = new Stock();

        // добавляем витрины
        shop.addShowcase(ShowcaseTypes.Constructor, 10);
        shop.addShowcase(ShowcaseTypes.Doll, 10);
        shop.addShowcase(ShowcaseTypes.Robot, 10);

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
                        Toy toy = shop.getAPrizeToy();

                    }
                    view.showMessage("Игрушки розданы!");
                }
            }
        }
    }


    enum DollNames {
        Маша, Петя, Саша, Даша, Паша, Таша, Каша, Иваша, Аркаша, Ромаша, Зояша, Наташа, Мараша, Валяша,
        Катюша, Рома, Зоя, Мариша, Валя, Люся, Лида, Ксюша, Оля,
        Ляля, Даня, Лада, Таня, Вика, Максик, Юля, Дима, Женя, Жужа, Эля, Илюша, Кариша, Гриша, Гоша, Лампа, Ева, Уля, Зина,
        Йоля, Надя, Нюра, Зорик, Шура, Олежка, Галя, Нина, Дина, Серёжа, Вадик, Ярик, Федя, Филя, Киря, Кира, Лёня, Светик,
        Лёша, Тима, Тома, Жора, Чита, Христя, Кристя, Толя, Хабибка, Лёва, Лина, Лена, Аля, Настя, Аня, Инна, Ира, Рита, Майя,
        Мила, Миша, Никита, Рая, Боря, Валька, Игорь, Марат, Родя, Юра, Слава, Люба, Люда, Поля, Соня, Софа, Лиза, Яна, Андрик,
        Арсений, Иося, Коля, Сёма, Вася, Веня, Витя, Владик, Егор
    }
}