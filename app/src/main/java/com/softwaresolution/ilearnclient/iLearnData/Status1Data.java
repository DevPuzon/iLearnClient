package com.softwaresolution.ilearnclient.iLearnData;

import java.util.ArrayList;
import java.util.List;

public class Status1Data {
    public static String mainlist;
    public static List<String> sublist = new ArrayList<String>();
    public static List<String> scorelist = new ArrayList<String>();

//    public Status1Data() {
//    }
//    public Status1Data(String mainlist,List<String>sublist,List<String>scorelist) {
//        this.mainlist =mainlist;
//        this.sublist = sublist;
//        this.scorelist =scorelist;
//    }

    public static String getMainlist() {
        return mainlist;
    }

    public static void setMainlist(String mainlist) {
        Status1Data.mainlist = mainlist;
    }

    public static List<String> getSublist() {
        return sublist;
    }

    public static void setSublist(List<String> sublist) {
        Status1Data.sublist = sublist;
    }

    public static List<String> getScorelist() {
        return scorelist;
    }

    public static void setScorelist(List<String> scorelist) {
        Status1Data.scorelist = scorelist;
    }
}
