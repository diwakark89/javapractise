package com.example.spliterator;

import com.example.spliterator.model.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CreatingSpliterator {
    public static void main(String[]args){
        Path path=Path.of("people.txt");
        try(Stream<String> lines= Files.lines(path);){
            Spliterator<String> lineSpliterator=lines.spliterator();
            Spliterator<Person> personSpliterator=new PersonSpliterator(lineSpliterator);
            Stream<Person>  people= StreamSupport.stream(personSpliterator,false);
            people.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
