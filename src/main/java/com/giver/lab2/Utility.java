// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab2;

import com.giver.lab1.Car;
import com.giver.lab1.Motorcycle;
import com.giver.lab1.Vehicle;
import com.giver.lab1.exceptions.DuplicateModelNameException;
import com.giver.lab1.exceptions.NoSuchModelNameException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Utility {
    public static double getAveragePrice(Vehicle vehicle){
        double avg = 0;
        double[] numbers = vehicle.getAllPrices();
        for (double number : numbers) {
            avg += number;
        }
        avg /= numbers.length;
        return avg;
    }
    public static void printAllModels(Vehicle vehicle){
        String[] names = vehicle.getAllModelNames();
        for(String m : names) System.out.println(m);
    }
    public static void printAllPrices(Vehicle vehicle){
        double[] prices = vehicle.getAllPrices();
        for(double p : prices) System.out.println(p);
    }
    public static void writeVehicleToByteStream(Vehicle vehicle, OutputStream outputStream){
        try(DataOutputStream dos = new DataOutputStream(outputStream)){
            String vehicleType = vehicle.getClass().getSimpleName();
            int strLength = 0;
            String data = vehicleType + "|"
                    + vehicle.getBrand() + "|"
                    + vehicle.getModelCount() + "|"
                    + Arrays.stream(vehicle.getAllModelNames())
                    .collect(Collectors.joining("#"))
                    + "|"
                    + Arrays.stream(vehicle.getAllPrices())
                    .mapToObj(Double::toString)
                    .collect(Collectors.joining("@"));
            strLength = data.length();
            byte[] dataBytes = data.getBytes();
//            dos.writeByte(strLength); //это по заданию но нет
            dos.write(dataBytes);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static Vehicle readVehicleFromByteStream(InputStream inputStream){
        Vehicle vehicle = null;
        int dataLength = 0;
        byte[] data = null;
        String[] dataParts = null;
        String[] modelNames = null;
        String[] modelPrices = null;
        double[] pricesParsed = null;
        String vehicleType = null;
        try(DataInputStream dataInputStream = new DataInputStream(inputStream)){
//            dataLength = dataInputStream.read(); //это было по заданию но нет
            data = dataInputStream.readAllBytes();
            String dataStr = new String(data, StandardCharsets.UTF_8);
//            dataStr = dataStr.substring(1); //лажа какая-то
            dataParts = dataStr.split("\\|");
            vehicleType = dataParts[0];
            switch(vehicleType){
                case "Car": vehicle = new Car(); break;
                case "Motorcycle": vehicle = new Motorcycle(); break;
            }
            vehicle.setBrand(dataParts[1]);
            modelNames = dataParts[3].split("\\#");
            modelPrices = dataParts[4].split("\\@");
            pricesParsed = Arrays.stream(modelPrices)
                    .mapToDouble(Double::parseDouble)
                    .toArray();
            for(int i=0; i< modelPrices.length; i++){
                vehicle.addModel(modelNames[i], pricesParsed[i]);
            }
        }catch(IOException e){
            e.printStackTrace();
        } catch (DuplicateModelNameException e) {
            throw new RuntimeException(e);
        }
        return vehicle;
    }
    public static void writeVehicleToSymbolStream(Vehicle vehicle, Writer writer){
        PrintWriter pw = new PrintWriter(writer);
        try{
//            int strLength = 0;
            String vehicleType = vehicle.getClass().getSimpleName();
            String data = vehicleType + "|"
                    + vehicle.getBrand() + "|"
                    + vehicle.getModelCount() + "|"
                    + Arrays.stream(vehicle.getAllModelNames())
                    .collect(Collectors.joining("#"))
                    + "|"
                    + Arrays.stream(vehicle.getAllPrices())
                    .mapToObj(Double::toString)
                    .collect(Collectors.joining("@"));
//            strLength = data.length();
//            pw.write(strLength); //по заданию но нет
            pw.write(data);
            pw.write("\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally{
            pw.flush();
        }
    }
    public static Vehicle readVehicleFromSymbolStream(Reader reader){
        Vehicle vehicle = null;
        String data = null;
        int modelCount = 0;
        String vehicleType = null;
        String[] dataParts = null;
        String[] modelNames = null;
        String[] modelPrices = null;
        double[] pricesParsed = null;
        try(BufferedReader br = new BufferedReader(reader)){
            data = br.readLine();
            dataParts = data.split("\\|");
            vehicleType = dataParts[0];
            switch(vehicleType){
                case "Car":
                    vehicle = new Car();
                break;
                case "Motorcycle":
                    vehicle = new Motorcycle();
                break;
            }
            vehicle.setBrand(dataParts[1]);
            modelCount = Integer.parseInt(dataParts[2]);
            modelNames = dataParts[3].split("\\#");
            modelPrices = dataParts[4].split("\\@");
            pricesParsed = Arrays.stream(modelPrices)
                    .mapToDouble(Double::parseDouble)
                    .toArray();
            for(int i=0; i< modelCount; i++){
                vehicle.addModel(modelNames[i], pricesParsed[i]);
            }
        }catch (DuplicateModelNameException e) {
            throw new RuntimeException(e);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return vehicle;
    }
}