package net._4werners.SCD;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SCDConfigController {
	private SCDConfigDataAccess configDataAccess;
	
    @FXML
    private TableView<SCDConfigData> scd_configtable;
	
   
    @FXML 
    private MenuBar scdConfigMenuBar;
	
	public void initialize() {
		
		showConfig();
		scd_configtable.setEditable(true);
		scd_configtable.getSelectionModel().cellSelectionEnabledProperty().set(true);
		
	} // initialize
	
	private void showConfig() {
		configDataAccess = new SCDConfigDataAccess();
		configDataAccess.loadData();
		scd_configtable.setItems(configDataAccess.getScdConfig());
	} // showConfig
	
	@FXML
	public void saveConfig() throws IOException {
		SCDController mainController = new SCDController();
		configDataAccess.saveData(SCDController.configFile);
		closeConfig();
		mainController.readConfigFile(SCDController.configFile);
	}//saveConfig
	
	@FXML
	public void showAbout() {
    	App.aboutSCD();
	}//showAbout
	
	@FXML
	public void closeConfig() {
		Stage stage = (Stage) scdConfigMenuBar.getScene().getWindow();
		stage.close();
	}//closeConfig
	
	
    @FXML
    private void showInfo() {
		String sText;
		
		sText = "Basepath:\t\tWhere youwant to put your files.\n";
		sText += "\t\t\tYour home directory would be a good place.\n";
		sText += "Schoolfiles:\tA filter to display only a few files in the\n";
		sText += "\t\t\tfile open dialog\n";
		sText += "\t\t\tExamples: s*.txt, school*.txt\n";
		sText += "Basename:\tYour files will always begin with the same name.\n";
		sText += "\t\t\tHere you tell that part of the filename\n";
		sText += "\t\t\tExamples: s_, school_\n";
		sText += "Defaultfile:\tThe file you want to load\n";
		sText += "\t\t\twhen the program starts.\n";
		
		Alert info = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) info.getDialogPane().getScene().getWindow();
        info.setTitle("Info about the program");
        stage.getIcons().add(new Image("bus.png"));
        info.setHeaderText(null);
        info.setContentText(sText);
        info.showAndWait();
		

	}	
} //Class
