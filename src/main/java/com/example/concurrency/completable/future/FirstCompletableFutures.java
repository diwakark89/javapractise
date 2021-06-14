package com.example.concurrency.completable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class FirstCompletableFutures {
    public static void main(String [] args) throws InterruptedException {

        ExecutorService executor= Executors.newSingleThreadExecutor();
        Supplier<String> task= ()-> {

            try {
                System.out.println("Running supplier");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return Thread.currentThread().getName();
        };

        CompletableFuture<String> future= CompletableFuture.supplyAsync(task,executor);
//        String str=future.join();
//        System.out.println("Result= "+str);
        executor.shutdown();

    }
}
