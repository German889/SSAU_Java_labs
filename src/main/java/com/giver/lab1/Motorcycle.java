// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab1;

import java.time.LocalDateTime;

public class Motorcycle {
    public Motorcycle(){}
    public Motorcycle(String brand){
        this.brand = brand;
        head = new Model();
    }
    private LocalDateTime lastModifiedBrandDate;
    {
        lastModifiedBrandDate = LocalDateTime.now();
    }
    private class Model {
        private LocalDateTime lastModifiedModelDate;
        {
            lastModifiedModelDate = LocalDateTime.now();
        }
        String modelName = null;
        double price = Double.NaN;
        Model prev = null;
        Model next = null;

        public void setModelName(String modelName){
            this.modelName = modelName;
        }
        public String getModelName(){
            return this.modelName;
        }
        public Model getNext(){
            return this.next;
        }
        public Model getPrev(){
            return this.prev;
        }
        public void setNext(Model model){
            this.next = model;
        }
        public void setPrev(Model model){
            this.prev = model;
        }

    }
    private int size = 0;
    private Model head = null;
    private String brand;
    public String getBrand(){
        return this.brand;
    }
    public void setBrand(String brandName){
        this.brand = brandName;
    }

    public void setHead(Model head){
        this.head = head;
    }
    public Model getHead(){
        return this.head;
    }
    public void add(Model model){
        if(head.getNext() == null){
            head.setNext(model);
            head.setPrev(model);
        }
        Model current = head;
        while (head.getPrev() != current){
            current = head.getNext();
        }
        current.setNext(model);
        head.setPrev(model);
    }
    public String[] getAllModelNames(){
        if(head == null) return new String[0];
        int modelsCount = 0;
        Model current = head;
        while(current != this.head.getPrev()){
            modelsCount++;
            current = current.getNext();
        }
        String[] allModelNames = new String[modelsCount];
        current = head;
        int i = 0;
        while(current != this.head.getPrev()){
            allModelNames[i] = current.getModelName();
        }
        return allModelNames;
    }

}
