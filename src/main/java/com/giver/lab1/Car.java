// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab1;

public class Car {
    int modelsCount = 0;
    public Car(){}
    public Car(String brand, int modelsCount){
        this.brand = brand;
        this.modelsCount = modelsCount;
    }
    GiverArrayList<Model> models = new GiverArrayList<>(modelsCount);
    private String brand;
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void createModel(String modelName, int price){
        Model model = new Model(modelName, price);
        models.add(model);
    }
    public void deleteModel(String modelName){
        final int len = models.size();
        for(int i=0; i<len; i++){
            if(models.get(i).getModelName().equals(modelName)){
                models.remove(i);
            }
        }
    }
    public int getModelsCount(){
        return models.size();
    }
    class Model{
        private String modelName;
        private int price;
        public Model(String modelName, int price) {
            this.modelName = modelName;
            this.price = price;
        }
        public void setModelName(String modelName) {
            this.modelName = modelName;
        }
        public String getModelName(){
            return this.modelName;
        }
        public int getPrice(){
            return this.price;
        }
        public void setPrice(int price){
            this.price = price;
        }
    }
    public String[] getAllModelNames(){
        final int len = models.size();
        String[] modelNames = new String[len];
        for(int i=0; i<len; i++){
            modelNames[i] = models.get(i).getModelName();
        }
        return modelNames;
    }
    public int[] getAllPrices(){
        final int len = models.size();
        int[] prices = new int[len];
        for(int i=0; i<len; i++){
            prices[i] = models.get(i).getPrice();
        }
        return prices;
    }
    public int getPriceByModelName(String modelName){
        final int len = models.size();
        for(int i=0; i<len; i++){
            if(models.get(i).getModelName().equals(modelName)){
                return models.get(i).getPrice();
            }
        }
        return -1;
    }
    public int setPriceByModelName(String modelName, int price){
        final int len = models.size();
        for(int i=0; i<len; i++){
            if(models.get(i).getModelName().equals(modelName)){
                models.get(i).setPrice(price);
                return 0;
            }
        }
        return -1;
    }
}
