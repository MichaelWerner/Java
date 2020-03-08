package net._4werners.SCD;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class SCDController {
    public static String sFileName = "";

    public static String sBasepath;
    public static String sSchoolfiles;
    public static String sDefaultFile;
    public static String sBasename;
    public static File configFile;
    public static boolean lUseDefault = true;

    @FXML
    private TableView<SCDData> scd_table;

    @FXML
    private DatePicker EventDate;

    private SCDDataAccess dataAccess;
    private SCDConfigDataAccess configDataAccess;
    private Stage primaryStage;

    public void initialize(){
        
        String input;
        String sHomeDir;
        configDataAccess = new SCDConfigDataAccess();
        
        //get user home dir
        sHomeDir = System.getProperty("user.home");
        
        //create sub folder
        sHomeDir += "/AppData/Local/SCD";
        new File(sHomeDir).mkdirs();
        
        //check if config file exists
        configFile = new File(sHomeDir + "/scdconfig.ini");
        if(configFile.exists() && !configFile.isDirectory()) { 
        	readConfigFile(configFile);
        } //if (config file found)
        else {
            try ( InputStream configFileStream = getFileFromResources("scdconfig.ini");
              	  BufferedReader br = new BufferedReader(new InputStreamReader(configFileStream));
              		){
              	
                  while (br.ready()) {
                  	input = br.readLine();
                      if (input.trim().length() > 0) {
                    	  fillConfig(input);
                      } // if 
                  } // while
                  
                  configFile.createNewFile();
                  configDataAccess.createConfig(configFile);
                  askModifyConfig();
              } catch(IOException e) {
                  System.out.println(e.getMessage());
              }
        } //else (config file not found)
        
        //open the file with the cancellations and delays
        OpenFile();
    }

    @FXML
    public void btnNo_clicked(){
        addEvent(1);
    }

    @FXML
    public void btn2hours_clicked(){
        addEvent(2);
    }

    @FXML
    public void btnEarly_clicked(){
        addEvent(3);
    }

    @FXML
    public void deleteEvent(){
        SCDData selectedEvent = scd_table.getSelectionModel().getSelectedItem();

        if(selectedEvent == null){
            Alert noSelection = new Alert(Alert.AlertType.INFORMATION);
            noSelection.setTitle("No event selected");
            noSelection.setHeaderText(null);
            noSelection.setContentText("Please select the event you want to delete");
            noSelection.showAndWait();
            return;
        }

        Alert deleteEvent = new Alert(Alert.AlertType.CONFIRMATION);
        deleteEvent.setTitle("Delete Event");
        deleteEvent.setHeaderText(null);
        deleteEvent.setContentText("Are you sure you want to delete the event\n" + selectedEvent.getsDate() + " " + selectedEvent.getsType() + "?");

        Optional<ButtonType> answer = deleteEvent.showAndWait();

        if(answer.isPresent() && answer.get() == ButtonType.OK){
            dataAccess.deleteData(selectedEvent);
            try {
                dataAccess.saveData();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        } // if answer
    }

    @FXML
    public void OpenFile(){
        dataAccess = new SCDDataAccess();

        if(!lUseDefault) {
            FileChooser scdFiles = new FileChooser();
            scdFiles.setInitialDirectory(new File(sBasepath));
            scdFiles.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("School Files", sSchoolfiles),
                    new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
            );
            File scdFile = scdFiles.showOpenDialog(primaryStage);

            if (scdFile != null) {
                sFileName = scdFile.toString();
            }
        }
        else {
            sFileName = sBasepath + "\\" + sDefaultFile;
            lUseDefault = false;
        }
        if(sFileName != "") {
            try {
                dataAccess.loadData();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            scd_table.setItems(dataAccess.getScdEvents());
            EventDate.setValue(LocalDate.now());
        }
    } //OpenFile
    
    @FXML
    public void showAbout(){
    	
    	App.aboutSCD();
    }

    public void addEvent(int event){
        String sDate = EventDate.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String sEvent = "";
        switch(event) {
            case 1:
                sEvent = "No School";
                break;
            case 2:
                sEvent = "2 hour delay";
                break;
            case 3:
                sEvent = "Early dismissal";
                break;
        }

        dataAccess.addData(sDate, sEvent);
        try {
            dataAccess.saveData();
        }catch (IOException e){
            System.out.println("Error in addEvent: " + e.getMessage());
        }
    }

    @FXML
    public void closeApp(){
        Platform.exit();
    }
    
    private InputStream getFileFromResources(String FileName) {
        ClassLoader classLoader = getClass().getClassLoader();

        InputStream ConfigFile = classLoader.getResourceAsStream(FileName);
        if (ConfigFile == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return ConfigFile;
        }
    } //InputStream
    
    private void fillConfig(String input) {
        String[] line = input.split("=");
        switch(line[0]) {
            case "BASEPATH":
                sBasepath = line[1];
                break;
            case "SCHOOLFILES":
                sSchoolfiles = line[1];
                break;
            case "DEFAULTFILE":
                sDefaultFile = line[1];
                break;
            case "BASENAME":
            	sBasename = line[1];
                break;
        } //switch    	
    } // fillConfig
    
    private void askModifyConfig() {
    	
    	String sQuestion = "No configuration file was found. \n";
    	sQuestion += "Therefore a default file was created. \n";
    	sQuestion += "Do you want to modify this file now?";
    	
        Alert createConfigFile = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        createConfigFile.getButtonTypes().setAll(yesButton, noButton);
        createConfigFile.setTitle("Modify Config file");
        createConfigFile.setHeaderText(null);
        createConfigFile.setContentText(sQuestion);
        
        Optional<ButtonType> answer = createConfigFile.showAndWait();

        if(answer.isPresent() && answer.get() == yesButton){
        	showConfig();
        } // if answer    	
    }
    
    @FXML
    public void showConfig() {
    	Parent scdConfig;
    	Stage configStage = new Stage();
		try {
			scdConfig = FXMLLoader.load(getClass().getResource("scdConfig.fxml"));
        	Scene configScene = new Scene(scdConfig, 425, 275);
        	configStage.setScene(configScene);
        	configStage.getIcons().add(new Image("bus.png"));
        	configStage.setTitle("Config - School Cancellations and Delays");
        	configStage.show();	
        	
        } catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    public void readConfigFile(File configFile){
    	String input;
    	try (BufferedReader br = new BufferedReader(new FileReader(configFile))){
            while ((input = br.readLine()) != null) {
                if (input.trim().length() > 0) {
                	fillConfig(input);
                } //if    
            } //while
		} catch (IOException e) {
			 System.out.println(e.getMessage());
		}

    }//readConfigFile
    
    @FXML
    private void showInfo() {
		String sText;
		
		sText = "When started, the program shows the information of\n";
		sText += "the default file that is configured in the settings.\n";
		sText += "If no file is configured or the file is not found,\n";
		sText += "an empty table is shown.\n\n";
		sText += "You can open a different file to show its content.\n\n\n";
		sText += "File - Open:\tShows a file selection dialog and\n";
		sText += "\t\t\tlets you select the file of your choice\n";
		sText += "File - Settings:\tOpens the settings dialog\n";
		sText += "\t\t\tto configure the way the program works\n\n";
		
		Alert info = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) info.getDialogPane().getScene().getWindow();
        info.setTitle("Info about the program");
        stage.getIcons().add(new Image("bus.png"));
        info.setHeaderText(null);
        info.setContentText(sText);
        info.showAndWait();
		

	}
    
} //Class

