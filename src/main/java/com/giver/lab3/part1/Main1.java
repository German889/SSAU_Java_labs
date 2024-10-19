// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab3.part1;

import com.giver.lab1.Motorcycle;
import com.giver.lab1.Vehicle;

public class Main1 {
    public static void main(String[] args) {
        Vehicle veh = new Motorcycle("honda", 450000);
        ModelNameThread modelPrinter = new ModelNameThread(veh);
        PriceThread pricePrinter = new PriceThread(veh);
        modelPrinter.setPriority(Thread.MAX_PRIORITY);
        pricePrinter.setPriority(Thread.MIN_PRIORITY);
        modelPrinter.start();
        pricePrinter.start();

    }
}
