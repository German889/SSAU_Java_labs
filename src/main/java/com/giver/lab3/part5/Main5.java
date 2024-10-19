// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab3.part5;

import com.giver.lab1.Vehicle;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main5 {
    public static void main(String[] args) throws InterruptedException {
        String[] filenames = {"toyota.txt","geely.txt","haval.txt","LADA.txt","chevrolet.txt"};
        BlockingQueue<Vehicle> abq = new ArrayBlockingQueue<>(2);
        for(String f : filenames){
            f = "C:\\Users\\Givermaen\\Documents\\workDirecrory\\SSAU\\SSAU_Java_labs\\src\\main\\java\\com\\giver\\lab3\\part5\\files\\"+f;
            VehicleFileReader vr = new VehicleFileReader(f,abq);
            new Thread(vr).start();
        }
        Thread.sleep(2000);
        for(String f:filenames){
            VehicleFileWriter vwr = new VehicleFileWriter(abq);
            new Thread(vwr).start();
        }
    }
}
