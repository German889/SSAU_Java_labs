package com.giver.lab4.multi.server;
import java.net.ServerSocket;
import java.net.Socket;
public class MultiThreadedSocketServer {
    public static void main(String[] args) {
        int port = 5555;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен на порту " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключен: " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}