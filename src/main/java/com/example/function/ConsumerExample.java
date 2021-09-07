package com.example.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ConsumerExample {
    public static void main(String [] args){

//        invokeConsumer();
        chainingConsumer();
    }
    public static void invokeConsumer(){
        Consumer<String> printConsumer=t -> System.out.println(t);
        Stream<String> cities= Stream.of("Rewa","Hyderabad","Pune","Delhi");
        cities.forEach(printConsumer);
    }

    public static void chainingConsumer(){
        List<String> cities= Arrays.asList("Rewa","Hyderabad","Pune","Delhi");

        Consumer<List<String>> upperCaseConsumer = list->{
            for(int i=0;i<list.size();i++){
                list.set(i, list.get(i).toUpperCase());
            }
        };

        Consumer<List<String>> printConsumer=list -> list.forEach(System.out::println);
        upperCaseConsumer.andThen(printConsumer).accept(cities);

    }
}
