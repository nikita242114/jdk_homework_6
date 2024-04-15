package ru;

import java.util.concurrent.CountDownLatch;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static CountDownLatch latch = new CountDownLatch(4);

    public static void main(String[] args) {
        Paradox game = new Paradox();
        long startTime = System.currentTimeMillis();

//        game.startGame(10_000_000);
//        game.startGame(100_000_000);
//        game.startGame(100_000);
//        game.startGame(1_000_000);

        new Thread(() -> game.startGame(1_000_000)).start();
        new Thread(() -> game.startGame(10_000_000)).start();
        new Thread(() -> game.startGame(100_000_000)).start();
        new Thread(() -> game.startGame(100_000)).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime));
    }
}