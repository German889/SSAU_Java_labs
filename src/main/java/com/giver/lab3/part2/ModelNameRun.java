// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab3.part2;

public class ModelNameRun implements Runnable{
    private VehicleSynchronizer vehicleSynchronizer = null;
    public ModelNameRun(VehicleSynchronizer vehicleSynchronizer){
        this.vehicleSynchronizer = vehicleSynchronizer;
    }
    @Override
    public void run() {
        boolean isNotEnd = true;
        try {
            while (isNotEnd){
                isNotEnd = vehicleSynchronizer.canPrintModel();
                if(!isNotEnd) break;
                vehicleSynchronizer.printModel();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
