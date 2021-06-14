package com.example.stream;

import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class SimpleStream {
    public static void main( String [] args){
        LongStream.range(0,1000).boxed().collect(Collectors.toList());
    }
}
