// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab1;

import com.giver.lab1.exceptions.ModelPriceOutOfBoundsException;
import com.giver.lab1.exceptions.NoSuchModelNameException;

import java.time.LocalDateTime;

public class Motorcycle implements Vehicle {
    { // блок инициализации для записи даты первого изменения
        lastModified = System.currentTimeMillis();
    }

    public Motorcycle() {
    }

    public Motorcycle(String brand, String modelName, double price) {
        lastModified = System.currentTimeMillis();
        this.brand = brand;
        this.head = new MotoModel(modelName, price);
        this.head.setNext(head);
        this.head.setPrev(head);
        size = 1;
    }

    private String brand;

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public void setBrand(String name) {
        lastModified = System.currentTimeMillis();
        this.brand = name;
    }

    @Override
    public void changeModelName(String oldName, String newName) throws NoSuchModelNameException {
        boolean isFound = false;
        lastModified = System.currentTimeMillis();
        MotoModel current = head;
        do {
            if (current.getModelName().equals(oldName)) {
                current.setModelName(newName);
                isFound = true;
                break;
            }
            current = current.getNext();
        } while (current != head);
        if (!isFound) throw new NoSuchModelNameException("Невозможно изменить модель с таким именем");
    }

    @Override
    public String[] getAllModelNames() {
        String[] allModels = new String[size];
        MotoModel current = head;
        for (int i = 0; i < size; i++) {
            allModels[i] = current.getModelName();
            current = current.getNext();
        }
        return allModels;
    }

    @Override
    public double getModelPriceByName(String name) throws NoSuchModelNameException {
        MotoModel current = head;
        do {
            if (current.getModelName().equals(name)) return current.getPrice();
            current = current.getNext();
        } while (current != head);
        throw new NoSuchModelNameException("Модель с таким именем не найдена");
    }

    @Override
    public void setModelPriceByName(String name, double price) throws NoSuchModelNameException, ModelPriceOutOfBoundsException {
        if (price > Double.MAX_VALUE - 1000) throw new ModelPriceOutOfBoundsException("Цена слишком высока");
        lastModified = System.currentTimeMillis();
        MotoModel current = head;
        boolean isFound = false;
        do {
            if (current.getModelName().equals(name)) {
                current.setPrice(price);
                isFound = true;
                break;
            }
            current = current.getNext();
        } while (current != head);
        if (!isFound) throw new NoSuchModelNameException("Модель с таким именем не найдена");
    }

    @Override
    public double[] getAllPrices() {
        double[] allPrices = new double[size];
        MotoModel current = head;
        for (int i = 0; i < size; i++) {
            allPrices[i] = current.getPrice();
            current = current.getNext();
        }
        return allPrices;
    }

    @Override
    public void addModel(String name, double price) throws ModelPriceOutOfBoundsException {
        if (price > Double.MAX_VALUE - 1000) throw new ModelPriceOutOfBoundsException("Цена слишком высока");
        size++;
        lastModified = System.currentTimeMillis();
        MotoModel lastNew = new MotoModel(name, price);
        if (head == null) {
            head = lastNew;
            head.setNext(head);
            head.setPrev(head);
        } else {
            MotoModel lastCurrent = head.getPrev();
            lastCurrent.setNext(lastNew);
            lastNew.setPrev(lastCurrent);
            lastNew.setNext(head);
            head.setPrev(lastNew);
        }
    }

    @Override
    public void deleteModel(String name) throws NoSuchModelNameException {
        lastModified = System.currentTimeMillis();
        if (head == null) throw new NoSuchModelNameException("Список моделей пуст");
        MotoModel current = head;
        boolean isFound = false;
        do {
            if (current.getModelName().equals(name)) {
                size--;
                MotoModel prev = current.getPrev();
                MotoModel next = current.getNext();
                if (prev == current && next == current) {
                    head = null;
                } else {
                    prev.setNext(next);
                    next.setPrev(prev);
                    if (current == head) {
                        head = next;
                    }
                }
                isFound = true;
                break;
            }
            current = current.getNext();
        } while (current != head);
        if (!isFound) throw new NoSuchModelNameException("Модель с таким именем не найдена");
    }

    @Override
    public int getModelCount() {
        return size;
    }

    private class MotoModel extends Model {
        protected MotoModel(String modelName, double price) {
            this.modelName = modelName;
            this.price = price;
        }

        String modelName = null;
        double price = Double.NaN;
        MotoModel prev = null;
        MotoModel next = null;

        public MotoModel getPrev() {
            return this.prev;
        }

        public void setPrev(MotoModel prev) {
            this.prev = prev;
        }

        public MotoModel getNext() {
            return this.next;
        }

        public void setNext(MotoModel next) {
            this.next = next;
        }

        @Override
        public void setModelName(String name) {
            this.modelName = name;
        }

        @Override
        public String getModelName() {
            return this.modelName;
        }

        @Override
        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public double getPrice() {
            return this.price;
        }
    }

    private int size = 0;
    private MotoModel head;
    private long lastModified;
// далее код по заданию
}