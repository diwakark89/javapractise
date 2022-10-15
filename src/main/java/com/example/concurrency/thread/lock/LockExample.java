package com.example.concurrency.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private static ReentrantLock lock = new ReentrantLock(true);
    private Condition conditionMet = lock.newCondition();

    public static void main(String[] args) {
        LockExample pingPong = new LockExample();
        Thread t1 = new Thread(() -> pingPong.pingpong("Ping", 5));
        Thread t2 = new Thread(() -> pingPong.pingpong("Pong", 5));
        t1.start();
        t2.start();
    }

    public void pingpong(String s, int times) {
        int counter = 1;
        while (counter <= times) {
            run(s, counter);
            counter = counter + 1;
        }
    }

    public void run(String s, int counter) {
        lock.lock();
        try {
            conditionMet.await(1, TimeUnit.SECONDS);
            System.out.println(s + " (" + counter + ")");
            conditionMet.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
