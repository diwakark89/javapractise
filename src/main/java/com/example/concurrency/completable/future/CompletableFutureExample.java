package com.example.concurrency.completable.future;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Long> longList = LongStream.range(1, 1000)
                .boxed().collect(Collectors.toList());
        ExecutorService executor = Executors.newCachedThreadPool();
        String val=null;
//        val=completableWithExecutor_withRunAsync(longList, executor);
        completableWithExecutor_withRunAsync(longList, executor);
        System.out.println("Printing because can't wait for value : " + val);

    }


    public static void completableWithExecutor(List<Long> longList, ExecutorService executor) throws ExecutionException, InterruptedException {

        CompletableFuture<Void> future = CompletableFuture.allOf(CompletableFuture.supplyAsync(() -> longList, executor)
                .thenApply(list -> {
                    CompletableFuture.supplyAsync(() -> {
                        readUser(list);
                        return "Completed";
                    }, executor);
                    CompletableFuture.supplyAsync(() -> {
                        print(list);
                        return "Completed";
                    }, executor);
                    return null;
                }));
//        future.join();
        future.get();
        System.out.println("Execution completed of completableWithExecutor");
        executor.shutdown();
    }

    public static Void completableWithExecutor_withRunAsync(List<Long> longList, ExecutorService executor) throws ExecutionException, InterruptedException {

        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> modifyList(longList), executor)
                .thenRun(() -> {
                    CompletableFuture.runAsync(() -> {
                        readUser(longList);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }, executor);
                    CompletableFuture.runAsync(() -> {
                        print(longList);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }, executor);
                });
        return cf.get();
    }

    public static String completable_WithOutExecutor_withRunAsync(List<Long> longList) throws ExecutionException, InterruptedException {

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> longList)
                .thenApply(list -> {
                    CompletableFuture.runAsync(() -> {
                        readUser(list);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                    CompletableFuture.runAsync(() -> {
                        print(list);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                    return "Chaining Completed";
                });
//        cf.join();
        return cf.get();
    }

    public static void completableWithMultiTask(List<Long> longList) {
        CompletableFuture.allOf(
                CompletableFuture.supplyAsync(() -> {
                    readUser(longList);
                    return "Completed";
                }),
                CompletableFuture.supplyAsync(() -> {
                    print(longList);
                    return "Completed";
                })).join();
    }

    public static void completableWithRunAsync_MultiTaskUsingExecutor(List<Long> longList, ExecutorService executor) throws ExecutionException, InterruptedException {
       CompletableFuture<List<Long>> cf1=CompletableFuture.supplyAsync(() -> modifyList(longList), executor);
        System.out.println(" First supplier: "+cf1.get() );
        CompletableFuture.allOf(
                CompletableFuture.supplyAsync(() -> {
                    readUser(longList);
                    return "Completed";
                }, executor),
                CompletableFuture.supplyAsync(() -> {
                    print(longList);
                    return "Completed";
                }, executor)).join();

        executor.shutdown();

    }

    public static void completableWithRunAsync_MultiTaskUsingExecutor_WithSeq(List<Long> longList, ExecutorService executor) {
        CompletableFuture.allOf(
                CompletableFuture.supplyAsync(() -> {
                    readUser(longList);
                    return "Completed";
                }, executor),
                CompletableFuture.supplyAsync(() -> {
                    print(longList);
                    return "Completed";
                }, executor)).join();
        executor.shutdown();
    }

    private static void print(List<Long> list) {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list.forEach(item -> System.out.println(" Print: " + item));
    }

    private static List<User> readUser(List<Long> list) {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list.stream().map(item -> {
            System.out.println(" Read: " + item);
            User user = new User();
            user.setId(item);
            return user;
        }).collect(Collectors.toList());
    }

    private static List<Long> modifyList(List<Long> list) {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list.stream().map(item -> {
            item += 100;
            System.out.println("New value: " + item);
            return item;
        }).collect(Collectors.toList());
    }
}

class User {
    long id;

    public void setId(long id) {
        this.id = id;
    }
}
