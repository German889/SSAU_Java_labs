// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab3.part4;

import com.giver.lab1.Car;
import com.giver.lab1.Motorcycle;
import com.giver.lab1.Vehicle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main4 {
    public static void main(String[] args) {
        Vehicle vehicle1 = new Car("Toyota", 1);
        Vehicle vehicle2 = new Motorcycle("Honda",1);
        Vehicle vehicle3 = new Car("Ford",1);
        Vehicle vehicle4 = new Car("Chevrolet",1);

        ExecutorService executorService = Executors.newFixedThreadPool(2);


        VehiclePrinter printer1 = new VehiclePrinter(vehicle1);
        VehiclePrinter printer2 = new VehiclePrinter(vehicle2);
        VehiclePrinter printer3 = new VehiclePrinter(vehicle3);
        VehiclePrinter printer4 = new VehiclePrinter(vehicle4);

        executorService.submit(printer1);
        executorService.submit(printer2);
        executorService.submit(printer3);
        executorService.submit(printer4);

        executorService.shutdown();
    }
}
