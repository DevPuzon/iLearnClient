package com.softwaresolution.ilearnclient.iLearnData;

import java.util.ArrayList;
import java.util.List;

public class SubTopic {
    public static List<String> subtopic = new ArrayList<String>();

    public static List<String> getSubtopic() {
        return subtopic;
    }

    public static void setSubtopic(List<String> subtopic) {
        SubTopic.subtopic = subtopic;
    }
}
