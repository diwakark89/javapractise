package com.example.file.parser;

import com.example.file.model.MCQModel;
import com.example.file.reader.FileRead;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    public static void main(String[] args) {

        List<MCQModel> mcqModelList = parseFileToList();
        mcqModelList.forEach(System.out::println);

    }

    public static List<MCQModel> parseFileToList() {
        FileRead fr = new FileRead();
//        String filePath = "mcq/best_questions.txt";

        String filePath = "mcq/multiline_questions.txt";

        String questionPat = "Q)";
        String optionPat1 = "(A)";
        String optionPat2 = "(B)";
        String optionPat3 = "(C)";
        String optionPat4 = "(D)";
        String answerPat = "Ans)";
        List<MCQModel> mcqModelList = new ArrayList<>();
        int count = -1;

        InputStream is = fr.getFileFromResourceAsStream(filePath);
        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;

            MCQModel mcqModel = null;
            while ((line = reader.readLine()) != null) {

                if (line.startsWith(questionPat)) {
                    count=-1;
                    mcqModel = new MCQModel();
                    mcqModel.setQuestion(line);
                    mcqModelList.add(mcqModel);
                    count++;
                } else if (line.startsWith(optionPat1)) {
                    mcqModel.setOption1(line);
                    count++;
                } else if (line.startsWith(optionPat2)) {
                    mcqModel.setOption2(line);
                    count++;
                } else if (line.startsWith(optionPat3)) {
                    mcqModel.setOption3(line);
                    count++;
                } else if (line.startsWith(optionPat4)) {
                    mcqModel.setOption4(line);
                    count++;
                } else if (line.startsWith(answerPat)) {
                    mcqModel.setAnswer(line);
                    count++;
                } else {
                    if (count == 0) {
                        mcqModel.setQuestion(line);
                    } else if (count == 1) {
                        mcqModel.setOption1(line);
                    } else if (count == 2) {
                        mcqModel.setOption2(line);
                    } else if (count == 3) {
                        mcqModel.setOption3(line);
                    } else if (count == 4) {
                        mcqModel.setOption4(line);
                    }else if (count == 5) {
                        mcqModel.setAnswer(line);
                    }
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return mcqModelList;

    }

}
