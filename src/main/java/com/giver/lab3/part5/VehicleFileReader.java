// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab3.part5;

import com.giver.lab1.Car;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class VehicleFileReader implements Runnable{
    private BlockingQueue queue = null;
    private String filename;
    public VehicleFileReader(String filename, BlockingQueue queue){
        this.filename = filename; this.queue = queue;
    }
    @Override
    public void run() {
        Random random = new Random();
        try{
            File file = new File(filename);
            Scanner reader = new Scanner(file);
            Car car = new Car(reader.nextLine(),random.nextInt(500000));
            queue.put(car);
//            queue.add(car);
            System.out.println("Добавлен транспорт "+car.getBrand());
            reader.close();
        }catch(FileNotFoundException fne){fne.printStackTrace();}
        catch (InterruptedException e) {throw new RuntimeException(e);}
    }
}
