package com.giver.lab1;

import com.giver.lab1.exceptions.ModelPriceOutOfBoundsException;
import com.giver.lab1.exceptions.NoSuchModelNameException;

public interface Vehicle {
    public String getBrand();
    public void setBrand(String name);
    public void changeModelName(String oldName, String newName) throws NoSuchModelNameException;
    public String[] getAllModelNames();
    public double getModelPriceByName(String name) throws NoSuchModelNameException;
    public void setModelPriceByName(String name, double price) throws NoSuchModelNameException, ModelPriceOutOfBoundsException;
    public double[] getAllPrices();
    public void addModel(String name, double price) throws ModelPriceOutOfBoundsException;
    public void deleteModel(String name) throws NoSuchModelNameException;
    public int getModelCount();
    public default double getAveragePrice(){
        double avg = 0;
        double[] numbers = getAllPrices();
        for (double number : numbers) {
            avg += number;
        }
        avg /= numbers.length;
        return avg;
    }
    public default void printAllModels(){
        String[] names = getAllModelNames();
        for(String m : names) System.out.println(m);
    }
    public default void printAllPrices(){
        double[] prices = getAllPrices();
        for(double p : prices) System.out.println(p);
    }

}
