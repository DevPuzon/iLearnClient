package com.softwaresolution.ilearnclient.iLearnData;

import java.util.ArrayList;
import java.util.List;

public class QuizSecurity {
    public static List<String>quiz = new ArrayList<String>();

    public QuizSecurity() {
    }
    public static List<String> getQuiz() {
        return quiz;
    }

    public static void setQuiz(List<String> quiz) {
        QuizSecurity.quiz = quiz;
    }
}
