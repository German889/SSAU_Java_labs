package com.giver.lab4.multi.client;

public class DDoSer {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new SimpleUser()).start();
        }
    }
}
