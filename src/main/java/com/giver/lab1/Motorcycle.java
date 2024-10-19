// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab1;

import com.giver.lab1.exceptions.DuplicateModelNameException;
import com.giver.lab1.exceptions.ModelPriceOutOfBoundsException;
import com.giver.lab1.exceptions.NoSuchModelNameException;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class Motorcycle implements Vehicle, Serializable {
    private static final long serialVersionUID = 1L;
    {
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
    public Motorcycle(String brand, int modelCount){
        lastModified = System.currentTimeMillis();
        this.brand = brand;
        head = modelGenerator();
        for(int i=0; i<modelCount; i++){
            MotoModel m = modelGenerator();
            try{
                addModel(m.getModelName(), m.getPrice());
                if(i==modelCount){
                    head.setPrev(m);
                }
            }catch(DuplicateModelNameException e){
                e.printStackTrace();
            }
        }
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

    @Override //ауауау
    public void changeModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if(oldName.equals(newName)) throw new DuplicateModelNameException("В чём смысл?");
        boolean isFound = false;
        lastModified = System.currentTimeMillis();
        MotoModel current = head;
        MotoModel found = null;
        do {// сделать за 1 проход
            if (current.getModelName().equals(oldName)) {
                found = current;
                isFound = true;
            }
            if(current.getModelName().equals(newName)) throw new DuplicateModelNameException("Не выйдет!");
            current = current.getNext();
        } while (current != head);
        if(isFound){
            found.setModelName(newName);
        }
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
    public void setModelPriceByName(String name, double price) throws NoSuchModelNameException {
        if (price < 0) throw new ModelPriceOutOfBoundsException("У вас нет такого промокода");
        lastModified = System.currentTimeMillis();
        MotoModel current = head;
        boolean isFound = false;
        do {
            if (current.getModelName().equals(name)) {
                if(current.getPrice()==price) throw new ModelPriceOutOfBoundsException("такая цена уже была");
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
    public void addModel(String name, double price) throws DuplicateModelNameException{
        MotoModel current = head;
        if (price < 0) throw new ModelPriceOutOfBoundsException("У вас нет такого промокода");
        if(head!=null){
            for (int i = 0; i < size; i++) {
                if (current.getModelName().equals(name)) throw new DuplicateModelNameException("Уже есть такая модель");
                current = current.getNext();
            }
            size++;
            lastModified = System.currentTimeMillis();
            MotoModel lastNew = new MotoModel(name, price);
            if (head == null) {
                head = lastNew;
                head.setNext(head);
                head.setPrev(head);
            } else if (head.getPrev() == null) {
                head.setPrev(lastNew);
                head.setNext(lastNew);
            } else {
                MotoModel lastCurrent = head.getPrev();
                lastCurrent.setNext(lastNew);
                lastNew.setPrev(lastCurrent);
                lastNew.setNext(head);
                head.setPrev(lastNew);
            }
        }else{
            size++;
            this.head = new MotoModel(name, price);
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

    private class MotoModel extends Model implements Serializable{
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
    private transient long lastModified;

    private Set<String> usedModelNames = new HashSet<>();

    public MotoModel modelGenerator() {
        Random random = new Random();
        String modelName;
        do {
            modelName = generateUniqueModelName(random);
        } while (usedModelNames.contains(modelName));

        usedModelNames.add(modelName);

        double price = random.nextDouble() * 10453;
        return new MotoModel(modelName, price);
    }

    private String generateUniqueModelName(Random random) {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}