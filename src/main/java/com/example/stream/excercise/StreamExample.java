package com.example.stream.excercise;

import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StreamExample {
    String line = "The quick brown fox jumps over the lazy dog";

    public static void main(String[] args) {
        flatMapExample();
    }

    public static void flatMapExample() {
        Function<String, Stream<String>> splitIntoWords = line -> Pattern.compile(" ").splitAsStream(line);
    }
}

