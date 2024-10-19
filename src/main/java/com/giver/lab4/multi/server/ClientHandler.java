package com.giver.lab4.multi.server;

import com.giver.lab1.Vehicle;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {

            List<Vehicle> vehicles = (List<Vehicle>) ois.readObject();
            double priceSum = 0;
            int count = 0;

            for (Vehicle vehicle : vehicles) {
                double[] modelPrices = vehicle.getAllPrices();
                count += modelPrices.length;
                for (double price : modelPrices) {
                    priceSum += price;
                }
            }

            double priceAvg = priceSum / count;
            oos.writeDouble(priceAvg);
            System.out.println("Отправлено значение: " + priceAvg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}