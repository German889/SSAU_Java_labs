// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab3.part4;

import com.giver.lab1.Vehicle;

public class VehiclePrinter implements Runnable {
    private Vehicle vehicle;

    public VehiclePrinter(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        System.out.println("Brand: " + vehicle.getBrand());
    }
}
