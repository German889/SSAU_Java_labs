package com.giver.lab1;

import com.giver.lab1.exceptions.DuplicateModelNameException;
import com.giver.lab1.exceptions.ModelPriceOutOfBoundsException;
import com.giver.lab1.exceptions.NoSuchModelNameException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Vehicle car = new Car("toyota", 15);
        Vehicle bike = new Motorcycle("yamaha", 10);
        String[] allCarNames = car.getAllModelNames();
        String[] allBikeNames = bike.getAllModelNames();
        String carNameForDublication = allCarNames[2];
        String bikeNameForDublication = allBikeNames[3];
        for(String s:allCarNames) System.out.println(s);
        for(String n:allBikeNames) System.out.println(n);
        try {
//            car.changeModelName("r8","r9");
//            bike.changeModelName("gh", "hn");
//            car.changeModelName(carNameForDublication,allCarNames[4]);
//            bike.changeModelName(bikeNameForDublication, allBikeNames[6]);
//            car.addModel(carNameForDublication,-100);
//            bike.addModel("tr", -100);
//            car.addModel(carNameForDublication,100);
//            bike.addModel(bikeNameForDublication, 100);
//            car.deleteModel("gvhgvb");
//            bike.deleteModel("jkhgchvhj");
//            car.getModelPriceByName("sxfgcjdth");
//            bike.getModelPriceByName("liugdlwb");
//            car.setModelPriceByName("kjsdbkds",7675);
//            bike.setModelPriceByName("ewknfb",9766);
        }
//        catch (DuplicateModelNameException e) {e.printStackTrace();}
//        catch(ModelPriceOutOfBoundsException e){e.printStackTrace();}
//        catch (NoSuchModelNameException e) {e.printStackTrace();}

    }
}