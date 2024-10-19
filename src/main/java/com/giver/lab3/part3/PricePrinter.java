// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab3.part3;

import com.giver.lab1.Vehicle;

import java.util.concurrent.locks.ReentrantLock;

public class PricePrinter implements Runnable{
    ReentrantLock rt = null;
    Vehicle veh = null;
    public PricePrinter(ReentrantLock rtLog, Vehicle veh){
        this.rt = rtLog;
        this.veh = veh;
    }
    @Override
    public void run() {
        try{
            rt.lock();
            double[] prices = veh.getAllPrices();
            for (double price : prices) {
                System.out.println(price);
            }
        }finally{
            rt.unlock();
        }

    }

}
