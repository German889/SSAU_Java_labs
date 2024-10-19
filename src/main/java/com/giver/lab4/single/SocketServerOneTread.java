package com.giver.lab4.single;

import com.giver.lab1.Vehicle;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class SocketServerOneTread {
    public static void main(String[] args) {
        int port = 5555;
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен на порту " + port);
            while(true){
                try(Socket clientSocket = serverSocket.accept();
                    ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())){
                    System.out.println("К нам тут подключился: " + clientSocket.getInetAddress());
                    List<Vehicle> vehicles = (List<Vehicle>) ois.readObject();
                    double priceSum = 0;
                    int count = 0;
                    for(Vehicle vehicle : vehicles){
                        double[] modelPrices = vehicle.getAllPrices();
                        count += modelPrices.length;
                        for (double price : modelPrices) {
                            priceSum += price;
                        }
                    }
                    double priceAvg = priceSum / count;
                    oos.writeDouble(priceAvg);
                    System.out.println("Отправленное значение: " + priceAvg);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
