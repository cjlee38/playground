package com.example.javaadvancedplayground.virtual;

public class VirtualThreadIntro {
    public static void main(String[] args) throws InterruptedException {
        Thread vThread = Thread.ofVirtual().start(() -> {
            String name = Thread.currentThread().toString();
            // Strig name = Thread.currentThread().getName(); // it returns ""
            System.out.println("hello world : " + name);
        });
        boolean virtual = vThread.isVirtual();
        System.out.println("virtual = " + virtual);
        Thread.sleep(1000L);
    }
}
