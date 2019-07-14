package com.bsuir;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static final String INPUT_FILE_PATH = "src\\com\\bsuir\\files\\input.txt";
    public static final String OUTPUT_FILE_PATH = "src\\com\\bsuir\\files\\output.txt";

    public static void main(String[] args) {
        File inputFile = new File(INPUT_FILE_PATH);
        File outputFile = new File(OUTPUT_FILE_PATH);
        List<String> fileLinesList = FileOperations.readFileToList(inputFile);

        List<AutoBus> busList = new ArrayList<>();

        for (String row:fileLinesList) {
            AutoBus bus = new AutoBus(row);
            busList.add(bus);
        }

        //Сортировка по arrival time

        Collections.sort(busList);

        //Initialization of bus.addToTimeTable parameter

        List<AutoBus> correctList = AutoBus.getEfficientAutoBusList(busList);

        Collections.sort(correctList,AutoBus.companyComparator);
        FileOperations.exportTimetableToFile(outputFile,correctList);

    }
}
