package com.example.concurrency.completable.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureTraining {
    public static void main(String [] args) throws ExecutionException, InterruptedException {
        runExecutorService();
    }
    public static void runExecutorService() throws ExecutionException, InterruptedException {
        ExecutorService service= Executors.newSingleThreadExecutor();
        Runnable task=()-> System.out.println("Guten Morgen");
        Future future=service.submit(task);
        future.get();
        service.shutdown();
    }
}
