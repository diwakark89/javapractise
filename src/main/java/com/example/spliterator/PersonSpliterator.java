package com.example.spliterator;

import com.example.spliterator.model.Person;

import java.util.Spliterator;
import java.util.function.Consumer;

public class PersonSpliterator implements Spliterator<Person> {
    private final Spliterator<String> lineSpliterator;

    public PersonSpliterator(Spliterator<String> lineSpliterator) {
        this.lineSpliterator=lineSpliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Person> action) {
        return false;
    }

    @Override
    public Spliterator<Person> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return lineSpliterator.estimateSize()/3;
    }

    @Override
    public int characteristics() {
        return lineSpliterator.characteristics();
    }
}
