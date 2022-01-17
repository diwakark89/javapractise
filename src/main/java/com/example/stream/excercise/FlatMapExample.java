package com.example.stream.excercise;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FlatMapExample {
    public static void main(String[] args) throws IOException, URISyntaxException {
        FlatMapExample app = new FlatMapExample();

        Stream<String> stream1 = app.getFileAsStream("files/TomSawyer_01.txt");


        Stream<String> stream2 = app.getFileAsStream("files/TomSawyer_02.txt");
        Stream<String> stream3 = app.getFileAsStream("files/TomSawyer_03.txt");
        Stream<String> stream4 = app.getFileAsStream("files/TomSawyer_04.txt");

//        System.out.println(stream1.count());
//        System.out.println(stream2.count());
//        System.out.println(stream3.count());
//        System.out.println(stream4.count());

        Stream<Stream<String>> streamOfStream = Stream.of(stream1, stream2, stream3, stream4);

        Stream<String> streamOfLines = streamOfStream.flatMap(Function.identity());
//        System.out.println(streamOfLines.count());
        Function<String,Stream<String>> lineSplitter= line-> Pattern.compile(" ").splitAsStream(line);

        Stream<String> streamOfWords = streamOfLines.flatMap(lineSplitter)
                .map(String::toLowerCase)
                .filter(word->word.length()==4)
                .distinct();
        System.out.println(streamOfWords.count());
    }


    private Stream<String> getFileAsStream(String fileName) throws URISyntaxException, IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            return Files.readAllLines(new File(resource.toURI()).toPath(), StandardCharsets.UTF_8).stream();
        }

    }

}
