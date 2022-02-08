package com.example.stream.excercise;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class SmallCodeExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 4, 10, 12, 87,12,4,5);
//        printSumOfAllIntegers(numbers);
//        printDateOfNextOccurringWednesday();
        removeDuplicate(numbers);
    }

    private static void removeDuplicate(List<Integer> numbers) {
        numbers.stream().collect(Collectors.toSet()).forEach(System.out::println);
    }

    /**
     * Print sum of all integers
     */
    static void printSumOfAllIntegers( List<Integer> numbers) {
        IntSummaryStatistics stats = numbers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("Sum of all numbers : " + stats.getSum());
    }

    /**
     * Print sum of all integers
     */
    static void printSumOfAllIntegers() {
        List<Integer> numbers = Arrays.asList(5, 4, 10, 12, 87, 33, 75);
        IntSummaryStatistics stats = numbers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("Sum of all numbers : " + stats.getSum());
    }

    /**
     * Print Date of Next Occurring Wednesday
     */
    static void printDateOfNextOccurringWednesday(){
        LocalDate today = LocalDate.now();
        LocalDate nextWednesday =
                today.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        System.out.println("Next Wednesday on : " + nextWednesday);
    }

}

