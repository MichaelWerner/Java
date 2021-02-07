package net._4werners.supi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DataEntryController {
	private SupplyDataAccess dataAccess;
	
	@FXML
	private TextField fxNewPart;

	@FXML
	private TextField fxNewType;

	@FXML
	private TextField fxNewPlace;
	
	@FXML
	private TextField fxNewShelf;
	
	@FXML
	private TextField fxNewContainer;
	
	@FXML
	private TextField fxNewQuantity;
	
	@FXML
	public void closeEntry(ActionEvent event) {
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}//closeEntry
	
	@FXML
	private void addEntry(ActionEvent event) {
		
		dataAccess = new SupplyDataAccess();
		dataAccess.addData(	String.valueOf(fxNewPart.getText()), 
							String.valueOf(fxNewType.getText()), 
							String.valueOf(fxNewPlace.getText()), 
							String.valueOf(fxNewShelf.getText()), 
							String.valueOf(fxNewContainer.getText()), 
							String.valueOf(fxNewQuantity.getText())
						  );
	}//addEntry
}