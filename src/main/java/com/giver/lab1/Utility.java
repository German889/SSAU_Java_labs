// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab1;

public class Utility{
    public static double getAveragePrice(Vehicle vehicle){
        double avg = 0;
        double[] numbers = vehicle.getAllPrices();
        for (double number : numbers) {
            avg += number;
        }
        avg /= numbers.length;
        return avg;
    }
    public static void printAllModels(Vehicle vehicle){
        String[] names = vehicle.getAllModelNames();
        for(String m : names) System.out.println(m);
    }
    public static void printAllPrices(Vehicle vehicle){
        double[] prices = vehicle.getAllPrices();
        for(double p : prices) System.out.println(p);
    }
}
