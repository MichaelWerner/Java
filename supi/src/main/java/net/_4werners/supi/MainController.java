package net._4werners.supi;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;



public class MainController {

    @FXML
    private TableView<SupplyData> tblSupplies;

    private SupplyDataAccess dataAccess;

    
    @FXML
    private void updateQty(CellEditEvent<SupplyData,String> event) {
    	//this defines ChangedSupply and writes the current row of the table to it
    	SupplyData ChangedSupply = event.getRowValue();
    	
    	//this updates the observable list
    	ChangedSupply.setsQuantity(event.getNewValue());
    	
    	//this updates the database
    	dataAccess.updateData(ChangedSupply);
    }
    
    
    @FXML
    private void showData() {
    	dataAccess.showData();
    }
    
    
    public void initialize(){
        dataAccess = new SupplyDataAccess();
        dataAccess.loadData();
        tblSupplies.setItems(dataAccess.getSupplies());
    }

    public void addEntry(){
    	Parent entry;
    	Stage entryStage = new Stage();
		try {
			entry = FXMLLoader.load(getClass().getResource("entry.fxml"));
        	Scene entryScene = new Scene(entry, 425, 275);
        	entryStage.setScene(entryScene);
        	entryStage.getIcons().add(new Image("stock.jpg"));
        	entryStage.setTitle("New Inventory - Inventorylist");
        	entryStage.showAndWait();
        	initialize();
        	
        	
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    public void closeApp(){
        Platform.exit();
    }
    

    
} //class
