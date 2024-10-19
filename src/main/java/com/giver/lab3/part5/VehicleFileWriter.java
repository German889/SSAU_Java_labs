// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab3.part5;

import com.giver.lab1.Car;
import com.giver.lab1.Vehicle;

import java.util.concurrent.BlockingQueue;

public class VehicleFileWriter implements Runnable{
    private BlockingQueue queue;
    public VehicleFileWriter(BlockingQueue queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try{
            Vehicle vh = (Car)queue.take();
            System.out.println("Взят транспорт "+vh.getBrand());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
