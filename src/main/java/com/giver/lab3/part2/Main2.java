// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab3.part2;

import com.giver.lab1.Motorcycle;
import com.giver.lab1.Vehicle;

public class Main2 {
    public static void main(String[] args) {
        Vehicle veh = new Motorcycle("honda", 5);
        VehicleSynchronizer vehicleSynchronizer = new VehicleSynchronizer(veh);
        ModelNameRun mnr = new ModelNameRun(vehicleSynchronizer);
        PriceRun pr = new PriceRun(vehicleSynchronizer);
        Thread modelThread = new Thread(mnr);
        Thread priceThread = new Thread(pr);
        modelThread.start();
        priceThread.start();
    }
}
