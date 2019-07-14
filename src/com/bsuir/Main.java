package com.bsuir;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private static final String INPUT_FILE_PATH = "src\\com\\bsuir\\files\\input.txt";
    private static final String OUTPUT_FILE_PATH = "src\\com\\bsuir\\files\\output.txt";

    public static void main(String[] args) {
        File inputFile = new File(INPUT_FILE_PATH);
        File outputFile = new File(OUTPUT_FILE_PATH);
        List<String> fileLinesList = FileOperations.readFileToList(inputFile);

        //parsing data from input file and creating AutoBusList

        List<AutoBus> busList = new ArrayList<>();
        for (String row:fileLinesList) {
            AutoBus bus = new AutoBus(row);
            busList.add(bus);
        }

        //sort by arrival time

        Collections.sort(busList);

        //Get correct TimeTable presented in AutoBuses

        List<AutoBus> correctList = AutoBus.getEfficientAutoBusList(busList);

        //sort TimeTable by Company name

        Collections.sort(correctList,AutoBus.companyComparator);

        //export data to outputFile

        FileOperations.exportTimetableToFile(outputFile,correctList);

    }
}
