package com.example.file.reader;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileRead {
    public static void main(String[] args) throws IOException, URISyntaxException {

        FileRead app = new FileRead();

        //String fileName = "database.properties";
        String fileName = "mcq/best_questions.txt";

        System.out.println("getResourceAsStream : " + fileName);
        InputStream is = app.getFileFromResourceAsStream(fileName);
        printInputStream(is);

        System.out.println("\ngetResource : " + fileName);
        File file = app.getFileFromResource(fileName);

        app.printFileContentUsingNIO(fileName);
        printFile(file);

    }
    
    public List<String> getFileContent(String filePath){

        System.out.println("\ngetResource : " + filePath);
        InputStream is = this.getFileFromResourceAsStream(filePath);
        printInputStream(is);

        File file = null;
        try {
            file = this.getFileFromResource(filePath);
        } catch (URISyntaxException e) {
            System.out.println("Exception occured" + e.getMessage());
        }

        return printFile(file);
    }

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    public InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    /*
        The resource URL is not working in the JAR
        If we try to access a file that is inside a JAR,
        It throws NoSuchFileException (linux), InvalidPathException (Windows)

        Resource URL Sample: file:java-io.jar!/json/file1.json
     */
    private File getFileFromResource(String fileName) throws URISyntaxException{

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }
    private void printFileContentUsingNIO(String fileName) throws IOException, URISyntaxException {
        Path path = Paths.get(getClass().getClassLoader()
                .getResource(fileName).toURI());

        Stream<String> lines = Files.lines(path);

        lines.map(line->{
            System.out.println(line);
            return line;
        }).collect(Collectors.joining("\n"));
        lines.close();
    }



    // print input stream
    private static void printInputStream(InputStream is) {

        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // print a file
    private static List<String> printFile(File file) {

        List<String> stringList= Collections.EMPTY_LIST;
        try {
            stringList=Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }
}
