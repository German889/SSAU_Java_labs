package com.giver.lab1;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Car bugatti = new Car("bugatti",3);
        bugatti.addModel("chiron",4567658);
        bugatti.addModel("veyron",7833637);
        String[] models = bugatti.getAllModelNames();
        for(String m : models) System.out.println(m);
        double[] prices = bugatti.getAllPrices();
        for(double p : prices) System.out.println(p);
        Vehicle vehicle = new Motorcycle("yamaha","ybr 125",88663.0);
        vehicle.addModel("suzuki", 22363);
        System.out.println("now motorcycle");
        System.out.println(vehicle.getAveragePrice());
    }
}