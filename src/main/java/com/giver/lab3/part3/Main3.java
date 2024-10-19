// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab3.part3;

import com.giver.lab1.Car;
import com.giver.lab1.Vehicle;

import java.util.concurrent.locks.ReentrantLock;

public class Main3 {
    private static final ReentrantLock rt = new ReentrantLock();
    public static void main(String[] args) {
        int whoIsFirst = 2;
        Vehicle vehicle = new Car("suzuki", 150);
        Thread modelPrinting = new Thread(new ModelNamePrinter(rt, vehicle));
        Thread pricePrinting = new Thread(new PricePrinter(rt, vehicle));
        switch(whoIsFirst){
            case 1: modelPrinting.start(); pricePrinting.start(); break;
            case 2: pricePrinting.start(); modelPrinting.start(); break;
        }
    }
}
