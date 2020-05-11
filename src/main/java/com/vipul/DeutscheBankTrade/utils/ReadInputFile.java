package com.vipul.DeutscheBankTrade.utils;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.opencsv.*;


public class ReadInputFile {

    public List<String[]> readFileData(String file)
    {
        try {
            System.out.println("Reading file " + file);
            FileReader filereader = new FileReader(file);
            System.out.println("File Read.. Opening CSV reader");
            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();


            List<String[]> allData = new ArrayList<>(csvReader.readAll());
            //alldata = csvReader.readAll().toArray();
            //Collections.copy(allData, csvReader.readAll());
//            ArrayList<String[]> allData = new ArrayList<>(Arrays.asList(csvReader.readAll()));

            // print Data
            for (String[] row : allData) {
                for (String cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
            filereader.close();
            return allData;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ArrayList<String > listFilesForFolder(final File folder) {
        ArrayList<String> fileList = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                fileList.add(fileEntry.getName());
                System.out.println(fileEntry.getName());
            }
        }
        return fileList;
    }

    public ArrayList<String []> readFilesFromFolder(final String path) {

        final File folder = new File(path);
        ArrayList<String > files =  listFilesForFolder(folder);
        ArrayList<String []> allFilesData = new ArrayList<>();
        for(String file: files){
            System.out.println("My file is " + file);
            ArrayList<String []> oneFileData = (ArrayList<String []>)(readFileData(path + "/" + file));
            allFilesData.addAll(oneFileData);
        }

        return allFilesData;
    }
}
