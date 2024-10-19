// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab3.part3;

import com.giver.lab1.Vehicle;

import java.util.concurrent.locks.ReentrantLock;

public class ModelNamePrinter implements Runnable{
    private ReentrantLock rt = null;
    private Vehicle veh = null;
    public ModelNamePrinter(ReentrantLock rtLog, Vehicle veh){
        this.rt = rtLog;
        this.veh = veh;
    }
    @Override
    public void run() {
        try{
            rt.lock();
            String[] models = veh.getAllModelNames();
            for (String model : models) {
                System.out.println(model);
            }
        }finally{
            rt.unlock();
        }

    }

}
