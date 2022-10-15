package com.example.file.model;

public class MCQModel {
    private StringBuilder question;
    private StringBuilder option1;
    private StringBuilder option2;
    private StringBuilder option3;
    private StringBuilder option4;

    public StringBuilder getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        if (this.question == null) {
            this.question = new StringBuilder(question);
        } else {
            this.question.append(question);
        }

    }

    public StringBuilder getOption1() {
        return option1;

    }

    public void setOption1(String option1) {

        if (this.option1 == null) {
            this.option1 = new StringBuilder(option1);
        } else {
            this.option1.append(option1);
        }
    }

    public StringBuilder getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        if (this.option2 == null) {
            this.option2 = new StringBuilder(option2);
        } else {
            this.option2.append(option2);
        }
    }

    public StringBuilder getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        if (this.option3 == null) {
            this.option3 = new StringBuilder(option3);
        } else {
            this.option3.append(option3);
        }
    }

    public StringBuilder getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        if (this.option4 == null) {
            this.option4 = new StringBuilder(option4);
        } else {
            this.option4.append(option4);
        }
    }

    @Override
    public String toString() {
        return "MCQModel{" +
                "question=" + question.toString() +
                ", option1=" + option1.toString() +
                ", option2=" + option2.toString() +
                ", option3=" + option3.toString() +
                ", option4=" + option4.toString() +
                '}';
    }
}
