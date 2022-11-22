package com.example.file.model;

public class MCQModel {
    private StringBuilder question;
    private StringBuilder option1;
    private StringBuilder option2;
    private StringBuilder option3;
    private StringBuilder option4;
    private StringBuilder answer;

    public MCQModel() {
        this.question = new StringBuilder();
        this.option1 = new StringBuilder();
        this.option2 = new StringBuilder();
        this.option3 = new StringBuilder();
        this.option4 = new StringBuilder();
        this.answer = new StringBuilder();
    }

    public StringBuilder getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer.append(answer);
    }

    public StringBuilder getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question.append(question);
    }

    public StringBuilder getOption1() {
        return option1 != null ? option1 : new StringBuilder();

    }

    public void setOption1(String option1) {
        this.option1.append(option1);
    }

    public StringBuilder getOption2() {
        return option2 != null ? option2 : new StringBuilder();
    }

    public void setOption2(String option2) {
        this.option2.append(option2);
    }

    public StringBuilder getOption3() {
        return option3 != null ? option3 : new StringBuilder();
    }

    public void setOption3(String option3) {
        this.option3.append(option3);
    }

    public StringBuilder getOption4() {
        return option4 != null ? option4 : new StringBuilder();
    }

    public void setOption4(String option4) {
        this.option4.append(option4);
    }

    @Override
    public String toString() {
        return "MCQModel{\n" +
                " question=" + question.toString() + "\n" +
                " option1=" + option1.toString() + "\n" +
                " option2=" + option2.toString() + "\n" +
                " option3=" + option3.toString() + "\n" +
                " option4=" + option4.toString() + "\n" +
                " answer=" + answer.toString() + "\n" +
                '}';
    }
}
