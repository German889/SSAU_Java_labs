// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab3.part1;

import com.giver.lab1.Vehicle;

public class ModelNameThread extends Thread{
    private String[] modelNames = null;
    public ModelNameThread(){}
    public ModelNameThread(Vehicle vehicle){
        this.modelNames = vehicle.getAllModelNames();
    }
    @Override
    public void run(){
        for(int i=0; i<modelNames.length; i++){
            System.out.println("название = "+modelNames[i]+" id="+i);
        }
    }
}
