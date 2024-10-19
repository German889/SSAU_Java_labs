package com.giver.lab4.single;

import com.giver.lab1.Car;
import com.giver.lab1.Vehicle;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 5555;
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            List<Vehicle> vehicles = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                vehicles.add(new Car("Audi",500));
            }
            oos.writeObject(vehicles);
            double averagePrice = ois.readDouble();
            System.out.println("Average price: " + averagePrice);
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
