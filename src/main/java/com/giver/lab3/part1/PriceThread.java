// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab3.part1;

import com.giver.lab1.Model;
import com.giver.lab1.Vehicle;

public class PriceThread extends Thread{
    private double[] modelPrices = null;
    public PriceThread(){}
    public PriceThread(Vehicle vehicle){
        this.modelPrices = vehicle.getAllPrices();
    }
    @Override
    public void run(){
        for(int i=0; i<modelPrices.length; i++){
            System.out.println("цена = "+modelPrices[i]+" id="+i);
        }
    }
}
