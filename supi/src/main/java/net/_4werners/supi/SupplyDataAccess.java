package net._4werners.supi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SupplyDataAccess {
	
	private final String sDB = "jdbc:sqlite:c:\\GIT\\Java\\supi\\src\\main\\resources\\supi.db";
	
    private ObservableList<SupplyData> supplies;

    public SupplyDataAccess() {
        supplies = FXCollections.observableArrayList();
    }

    public ObservableList<SupplyData> getSupplies() {
        return supplies;
    }

	public static Connection connectDB(String sDB) {
		Connection myDB = null;
		try {
			myDB = DriverManager.getConnection(sDB);
		}catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
		return myDB;
	}    
    
    public void loadData(){

        SupplyData data;
		
		String sQuery = "select * from supplies order by Artikel;";
		Connection myDB = connectDB(sDB);

        try{
        
        	ResultSet rs = myDB.createStatement().executeQuery(sQuery);
        	while(rs.next()) {
                data = new SupplyData(	rs.getString("Artikel"),
                						rs.getString("Kategorie"),
                						rs.getString("Ort"),
                						rs.getString("Fach"),
                						rs.getString("Behaelter"),
                						rs.getString("Ist"));
                supplies.add(data);
        	} //while
        	rs.close();
        	myDB.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }				
				
    }//loadData
    
    public void showData() {
    	supplies.forEach(child -> System.out.println(child.getsPart() + ": " + child.getsQuantity()));
    };
    
    public void updateData(SupplyData changedRecord) {
		String sQuery = "update supplies";
		sQuery += " set Ist = " + changedRecord.getsQuantity();
		sQuery += " where Artikel = '"  + changedRecord.getsPart() + "'";
		sQuery += " and Kategorie = '" + changedRecord.getsType() + "'";
		sQuery += " and Ort = '" + changedRecord.getsPlace() + "'";
		sQuery += " and Fach = '" + changedRecord.getsShelf() + "';";
		
        try{
    		Connection myDB = connectDB(sDB); 
        	myDB.createStatement().execute(sQuery);
        	myDB.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addData(String sNewPart, String sNewType, String sNewPlace, String sNewShelf, String sNewContainer, String sNewQuantity) {
    	if(sNewPart.isEmpty()) { sNewPart = "";}
    	if(sNewType.isEmpty()) { sNewType = "";}
    	if(sNewPlace.isEmpty()) { sNewPlace = "";}
    	if(sNewShelf.isEmpty()) { sNewShelf = "";}
    	if(sNewContainer.isEmpty()) { sNewContainer = "";}
    	if(sNewQuantity.isEmpty()) { sNewQuantity = "0";}
    	
    	String sQuery = "insert into supplies";
		sQuery += " (Artikel, Kategorie, Ort, Fach, Behaelter, Ist)";
		sQuery += " Values('"  + sNewPart + "'";
		sQuery += ", '" + sNewType + "'";
		sQuery += ", '" + sNewPlace + "'";
		sQuery += ", '" + sNewShelf + "'";
		sQuery += ", '" + sNewContainer + "'";
		sQuery += ", '" + sNewQuantity + "');";
		
        try{
    		Connection myDB = connectDB(sDB); 
        	myDB.createStatement().execute(sQuery);
        	myDB.close();
        	
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
} //class
