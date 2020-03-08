package net._4werners.SCD;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SCDDataAccess {
    private static final String SCD_Delimiter = "\t";

    private ObservableList<SCDData> scdEvents;

    public SCDDataAccess() {
        scdEvents = FXCollections.observableArrayList();
    }

    public ObservableList<SCDData> getScdEvents() {
        return scdEvents;
    }

    public void loadData() throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get(SCDController.sFileName));

        String input;
        SCDData data;
        try {
            while ((input = br.readLine()) != null) {
                if (input.trim().length() > 0) {
                    String[] line = input.split(SCD_Delimiter);
                    data = new SCDData(line[0], line[1]);
                    scdEvents.add(data);
                }
            }

        } finally {
            if(br != null) {
                br.close();
            }
        }
    }//loadData


    public void addData(String sDate, String sEvent){
        SCDData data;
        data = new SCDData(sDate, sEvent);
        scdEvents.add(data);
    }//addData

    public void deleteData(SCDData sEvent){
        scdEvents.remove(sEvent);
    }//deleteData

    public void saveData() throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(Paths.get(SCDController.sFileName));
        String output = "";

        try {
            for (SCDData data : scdEvents) {
                output = data.getsDate() + SCD_Delimiter + data.getsType() + "\n";
                bw.write(output);
            }
            bw.flush();
            bw.close();

        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    } //saveData
    
} //class
