// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab2;

import com.giver.lab1.Car;
import com.giver.lab1.Motorcycle;
import com.giver.lab1.Vehicle;
import com.giver.lab1.exceptions.ModelPriceOutOfBoundsException;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    System.out.println("Hello world!");
    Vehicle car = new Car("toyota", 15);
    Vehicle bike = new Motorcycle("yamaha", 10);
    String[] allCarNames = car.getAllModelNames();
    String[] allBikeNames = bike.getAllModelNames();
//    for(String s:allCarNames) System.out.println(s);
//    for(String n:allBikeNames) System.out.println(n);

    startTask1( (Motorcycle) bike,(Car)car);
    }
    public static void startTask1(Motorcycle bike, Car car){
        try {
            Utility.writeVehicleToByteStream(bike, new FileOutputStream("hello.some"));
            Vehicle readed = Utility.readVehicleFromByteStream(new FileInputStream("hello.some"));
            System.out.println("Класс: "+readed.getClass().getSimpleName()+"\n"+"Марка: "+readed.getBrand()+"\n"+"кол-во моделей: "+readed.getModelCount());

            Utility.writeVehicleToSymbolStream(car, new FileWriter("mosaic.some"));
            Vehicle readead = Utility.readVehicleFromSymbolStream(new FileReader("mosaic.some"));
            System.out.println("Класс: "+readead.getClass().getSimpleName()+"\n"+"Марка: "+readead.getBrand()+"\n"+"кол-во моделей: "+readead.getModelCount());

            System.out.println("Введите данные для Vehicle:");
            Vehicle readed1 = Utility.readVehicleFromSymbolStream(new InputStreamReader(System.in));
            System.out.println("again");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
            Utility.writeVehicleToSymbolStream(readed1, outputStreamWriter);
            System.out.println("распечаталось");
            System.out.println("распечаталось");
            System.out.println("распечаталось");

        }
//        catch (FileNotFoundException e) {throw new RuntimeException(e);}
//        catch (IOException e) {throw new RuntimeException(e);}
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void startTask2(Car car, Motorcycle moto){
        FileOutputStream fos,motofos = null;
        ObjectOutputStream oos,motoOOS = null;
        try {
            fos = new FileOutputStream("car.ser");
            motofos = new FileOutputStream("moto.ser");
            oos = new ObjectOutputStream(fos);
            motoOOS = new ObjectOutputStream(motofos);
            oos.writeObject(car);
            motoOOS.writeObject(moto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileInputStream fis, motofis = null;
        ObjectInputStream ois, motoOis = null;
        Car readCar = null;
        Motorcycle readMoto = null;
        try{
            fis = new FileInputStream("car.ser");
            motofis = new FileInputStream("moto.ser");
            ois = new ObjectInputStream(fis);
            motoOis = new ObjectInputStream(motofis);
            readCar = (Car) ois.readObject();
            System.out.println("Десериализованный кар ");
            System.out.println("брээнддд: "+readCar.getBrand());
            System.out.println("кол-во моделей: "+readCar.getModelCount());
            System.out.println("_________________");
            readMoto = (Motorcycle) motoOis.readObject();
            System.out.println("Десериализованный мопед ");
            System.out.println("брээнддд: "+readMoto.getBrand());
            System.out.println("кол-во моделей: "+readMoto.getModelCount());
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}