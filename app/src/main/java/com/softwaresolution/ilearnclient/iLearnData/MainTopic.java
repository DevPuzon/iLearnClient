package com.softwaresolution.ilearnclient.iLearnData;

import java.util.ArrayList;
import java.util.List;

public class MainTopic {
    public static List<String> list = new ArrayList<String>();

    public static List<String> getList() {
        return list;
    }

    public static void setList(List<String> list) {
        MainTopic.list = list;
    }
}
