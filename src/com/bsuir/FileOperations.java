package com.bsuir;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class FileOperations {

        //reading file by lines
        //returns List<string>

    static List<String> readFileToList(File file){
        List<String> resultList = new ArrayList<>();
        String fileLine;
        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((fileLine =bufferedReader.readLine()) != null){
                resultList.add(fileLine);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return resultList;
    }

    //Writing data from List<AutoBus> to outputFile

    static void exportTimetableToFile(File file, List<AutoBus> busList){
        boolean blankLinerequired = true;
        try(FileWriter fileWriter = new FileWriter(file,false)){
            for (AutoBus bus:busList) {
                if ((bus.getCompany().equals("Grotty")) && blankLinerequired){
                    fileWriter.append("\n");
                    blankLinerequired = false;
                }
                fileWriter.write(bus.toString());
                fileWriter.append("\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
