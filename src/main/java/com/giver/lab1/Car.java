// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab1;

import com.giver.lab1.exceptions.DuplicateModelNameException;
import com.giver.lab1.exceptions.ModelPriceOutOfBoundsException;
import com.giver.lab1.exceptions.NoSuchModelNameException;

import java.io.Serializable;
import java.util.*;

public class Car implements Vehicle, Serializable {
    private static final long serialVersionUID = 1L;
    public Car(){
        models = new CarModel[0];
    }
    public Car(String brand, int modelCount){
        this.brand = brand;
        models = new CarModel[modelCount]; //ауауауауауауауауауауаауауауауауауауауауа
        for(int i=0; i<models.length; i++) {
            models[i] = modelGenerator();
        }
    }
    private CarModel[] models;
    private String brand;

    private class CarModel extends Model implements Serializable{
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

    @Override //ауауауауаауауауауауауауауа
    public void changeModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if(oldName.equals(newName)) throw new DuplicateModelNameException("В чём смысл?");
//        boolean notFound = true;
        int foundedId = -1;
        for (int i=0; i<models.length; i++) { //можно сделать в 1 проход
            if(models[i].getModelName().equals(newName)) throw new DuplicateModelNameException("Не выйдет!");
            else if (models[i].getModelName().equals(oldName)) {
                foundedId = i;
//                break;
            }

        }
        if(foundedId > -1){
            models[foundedId].setModelName(newName);
        } else throw new NoSuchModelNameException("Невозможно изменить название модели");
    }

    @Override
    public String[] getAllModelNames() {
        return Arrays.stream(models)
                .filter(Objects::nonNull)
                .map(CarModel::getModelName)
                .toArray(String[]::new);
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
            NoSuchModelNameException {
        if(price < 0) throw new ModelPriceOutOfBoundsException("У вас нет такого промокода");
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
                .toArray();
    }


    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        if(price < 0) throw new ModelPriceOutOfBoundsException("У вас нет такого купона");
        if(models.length>0){
            for (CarModel m : models) {
                if (m.getModelName().equals(name)) throw new DuplicateModelNameException("Куда? Есть уже");
            }
            models = Arrays.copyOf(models, models.length + 1);
            CarModel nextModel = new CarModel(name, price);
            models[models.length-1] = nextModel;
        }else{
            models = new CarModel[1];
            CarModel firstModel = new CarModel(name, price);
            models[0] = firstModel;
        }
    }

    @Override
    public void deleteModel(String name) throws NoSuchModelNameException {
        for(int i=0; i<models.length; i++){
            if(models[i].getModelName().equals(name)){
                System.arraycopy(models, i + 1, models, i, models.length - 1 - i);
//                for(int j=i; j<models.length; j++){// system.arrays
//                    models[j] = models[j+1];
//                }
                models = Arrays.copyOf(models, models.length-1);
                return;
            }
        }
        throw new NoSuchModelNameException("Невозможно удалить, такого нет");
    }

    @Override
    public int getModelCount() {
        return models.length;
    }
    private Set<String> usedModelNames = new HashSet<>();

    public CarModel modelGenerator() {
        Random random = new Random();
        String modelName;
        do {
            modelName = generateUniqueModelName(random);
        } while (usedModelNames.contains(modelName));

        usedModelNames.add(modelName);

        double price = random.nextDouble() * 10453;
        return new CarModel(modelName, price);
    }

    private String generateUniqueModelName(Random random) {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
