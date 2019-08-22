package com.softwaresolution.ilearnclient.iLearnData;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    public static List<String> quizlist = new ArrayList<String>();

    public static List<String> getQuizlist() {
        return quizlist;
    }

    public static void setQuizlist(List<String> quizlist) {
        Quiz.quizlist = quizlist;
    }
}
