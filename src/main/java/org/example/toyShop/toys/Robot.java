package org.example.toyShop.toys;

import org.example.toyShop.ToyTypes;

public class Robot extends Toy implements HandsLegs {
    public Robot() {
        super(30, ToyTypes.Robot);
    }
}
