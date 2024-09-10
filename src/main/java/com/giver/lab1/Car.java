// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab1;

import com.giver.lab1.exceptions.ModelPriceOutOfBoundsException;
import com.giver.lab1.exceptions.NoSuchModelNameException;

import java.util.Arrays;
import java.util.Objects;

public class Car implements Vehicle {
    public Car(){}
    /*
    Конструктор класса должен принимать в качестве параметров значение Марки автомобиля и размер массива Моделей
    */
    public Car(String brand, int modelCount){
        this.brand = brand;
        models = new CarModel[modelCount];
    }
    private CarModel[] models;
    private String brand; //поле типа String, хранящее марку автомобиля

    //внутренний класс Модель, имеющий поля название модели и её цену, а также конструктор
    private class CarModel extends Model{
        public CarModel(){}
        public CarModel(String modelName, double price){
            this.modelName = modelName; this.price = price;
        }
        private String modelName;
        private double price;
        @Override
        public void setModelName(String name) {this.modelName = name;}
        @Override
        public String getModelName() {return this.modelName;}
        @Override
        public void setPrice(double price) {this.price = price;}
        @Override
        public double getPrice() {return this.price;}

    }
    @Override
    public String getBrand() {return this.brand;}
    @Override
    public void setBrand(String name) {this.brand = name;}

    @Override
    public void changeModelName(String oldName, String newName) throws NoSuchModelNameException {
        boolean notFound = true;
        for (CarModel model : models) {
            if (model.getModelName().equals(oldName)) {
                model.setModelName(newName);
                notFound = false;
            }
        }
        if(notFound) throw new NoSuchModelNameException("Невозможно изменить название модели");
    }

    @Override
    public String[] getAllModelNames() { // Используем StreamAPI
        return Arrays.stream(models)
                .filter(Objects::nonNull) // Фильтруем только непустые элементы
                .map(CarModel::getModelName)//Преобразуем каждый элемент потока (объект CarModel) в его имя (строку) с помощью метода getModelName()
                .toArray(String[]::new); //Преобразуем поток обратно в массив строк.
    }

    @Override
    public double getModelPriceByName(String name) throws NoSuchModelNameException {
        for (CarModel model : models){
            if(model.getModelName().equals(name)) return model.getPrice();
        }
        throw new NoSuchModelNameException("Невозможно получить цену по названию");
    }

    @Override
    public void setModelPriceByName(String name, double price) throws
            NoSuchModelNameException, ModelPriceOutOfBoundsException {
        if(price > Double.MAX_VALUE - 1000) throw new ModelPriceOutOfBoundsException("Чё так дорого????");
        boolean isFound = false;
        for(CarModel model : models)
            if(model.getModelName().equals(name)) {
                model.setPrice(price);
                isFound = true;
            }

        if (!isFound) throw new NoSuchModelNameException("Невозможно установить цену, такого названия нет");
    }

    @Override
    public double[] getAllPrices() {
        return Arrays.stream(models)
                .filter(Objects::nonNull)
                .mapToDouble(CarModel::getPrice)
                .toArray();  // Преобразуем поток double в массив double[]
    }


    @Override
    public void addModel(String name, double price) throws ModelPriceOutOfBoundsException {
        if(price > Double.MAX_VALUE - 1000) throw new ModelPriceOutOfBoundsException("Чё так дорого????");
        CarModel[] models2 = Arrays.copyOf(models, models.length+1);
        CarModel nextModel = new CarModel(name, price);
        models2[models.length] = nextModel;
        models = models2;
    }

    @Override
    public void deleteModel(String name) {
        models = Arrays.copyOf(models, models.length-1);
    }

    @Override
    public int getModelCount() {
        return models.length;
    }
}
