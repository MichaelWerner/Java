package net._4werners.SCD;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SCDConfigDataAccess {

	private static ObservableList<SCDConfigData> scdConfig;
	

	public SCDConfigDataAccess() {
		scdConfig = FXCollections.observableArrayList();
	}

	public ObservableList<SCDConfigData> getScdConfig() {
		return scdConfig;
	}

	public void loadData() {

		SCDConfigData data;
		data = new SCDConfigData("BASEPATH", SCDController.sBasepath, "The directory you want to put the files in");
		scdConfig.add(data);

		data = new SCDConfigData("SCHOOLFILES", SCDController.sSchoolfiles, "A pattern to find the files");
		scdConfig.add(data);

		data = new SCDConfigData("BASENAME", SCDController.sBasename, "The start of the filename. It will be extended by the school year.");
		scdConfig.add(data);

		data = new SCDConfigData("DEFAULTFILE", SCDController.sDefaultFile,
				"The file to be loaded when the program starts");
		scdConfig.add(data);

	} // loadData

	public void createConfig(File configFile) throws IOException {
		loadData();
		saveData(configFile);
	} //createConfig
	
    public void saveData(File configFile) {
		  
		  String output = "";
		  try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(configFile.getPath()));) { 
			  for (SCDConfigData data : scdConfig) { 
				  output = data.getsConfigType() + "=" + data.getsConfigValue() + "\n";
				  bw.write(output); } 
			  bw.flush();
		  }catch(IOException e){ System.out.println("Error: " + e.getMessage()); }
	} //saveData
	
}
