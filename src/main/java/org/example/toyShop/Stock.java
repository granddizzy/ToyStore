package org.example.toyShop;

import org.example.toyShop.toys.Constructor;
import org.example.toyShop.toys.Doll;
import org.example.toyShop.toys.Robot;
import org.example.toyShop.toys.Toy;

public class Stock {
    int lastToyId;

    public Stock() {
        this.lastToyId = 0;
    }

    public Toy takeFromWarehouse(ShowcaseTypes type) {
        Toy toy = null;

        switch (type) {
            case Doll: {
                toy = new Doll("Кукла", 30);
            }
            case Robot: {
                toy = new Robot("Робот", 30);
            }
            case Constructor: {
                toy = new Constructor("Конструктор", 30);
            }
        }

        toy.setId(this.getNewID());
        return toy;
    }

    public int getNewID() {
        return lastToyId + 1;
    }
}
