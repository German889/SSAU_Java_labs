// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package com.giver.lab7.task2;

import java.io.File;

public class Main7 {
    public static void main(String[] args) {
        String path = "src/main/java/com/giver/lab7/task1/student.xml";
        XMLAnalyzer xmln = new XMLAnalyzer(args[0],args[1]);
        double realAverage = xmln.calculateAverage();
        double declaredAverage = xmln.averageMarkInXML();
        if(realAverage != declaredAverage){
            xmln.writeAverageValue(realAverage);
        }
    }
}
