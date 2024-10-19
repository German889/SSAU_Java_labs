package com.giver.lab3.part2;

import com.giver.lab1.Vehicle;

public class VehicleSynchronizer {
    private Vehicle v;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = false;

    public VehicleSynchronizer(Vehicle v) {
        this.v = v;
    }

    public void printPrice() throws InterruptedException { //must be void
        double val;
        synchronized(lock) {
            double [] p = v.getAllPrices();
            while (!set)
                lock.wait();
            val = p[current++];
            System.out.println("Print price: " + val);
            set = false;
            lock.notifyAll();
        }
    }

    public void printModel() throws InterruptedException { //must be void
        synchronized(lock) {
            String [] s = v.getAllModelNames();
            while (set)
                lock.wait();
            System.out.println("Print model: " + s[current]);
            set = true;
            lock.notifyAll();
        }
    }

    public boolean canPrintPrice() {
        return current < v.getModelCount();
    }

    public boolean canPrintModel() {
        return (!set && current < v.getModelCount()) || (set && current < v.getModelCount() - 1);
    }

}