package com.example.function;

import java.util.Optional;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String [] args){
        supplierWithOptional();
    }

    private static void invokeSupplier() {
        Supplier<Double> doubleSupplier1=() -> Math.random();
        DoubleSupplier doubleSupplier2 = Math::random;
        System.out.println(doubleSupplier1.get());
        System.out.println(doubleSupplier2.getAsDouble());
    }

    private static void supplierWithOptional() {
        Supplier<Double> doubleSupplier1=() -> Math.random();
        Optional<Double> optionalDouble=Optional.empty();
        System.out.println(optionalDouble.orElseGet(doubleSupplier1));
    }
}
