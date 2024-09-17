package com.giver.lab1;

import com.giver.lab1.exceptions.DuplicateModelNameException;
import com.giver.lab1.exceptions.ModelPriceOutOfBoundsException;
import com.giver.lab1.exceptions.NoSuchModelNameException;

public interface Vehicle {
    public String getBrand();
    public void setBrand(String name);
    public void changeModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;//уаауауауауауауауауауауаауауауауа
    public String[] getAllModelNames();
    public double getModelPriceByName(String name) throws NoSuchModelNameException;
    public void setModelPriceByName(String name, double price) throws NoSuchModelNameException;
    public double[] getAllPrices();
    public void addModel(String name, double price) throws DuplicateModelNameException;
    public void deleteModel(String name) throws NoSuchModelNameException;
    public int getModelCount();

}
