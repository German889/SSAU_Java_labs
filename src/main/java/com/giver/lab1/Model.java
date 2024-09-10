// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab1;

public abstract class Model {
    private String modelName;
    private double price;
    protected Model(){}
    protected Model(String name, double price){
        this.modelName = name;
        this.price = price;
    }
    public abstract void setModelName(String name);
    public abstract String getModelName();
    public abstract void setPrice(double price);
    public abstract double getPrice();
}
