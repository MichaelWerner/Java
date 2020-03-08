package net._4werners.SCD;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    	Parent scdMain = FXMLLoader.load(getClass().getResource("scd.fxml"));
        scene = new Scene(scdMain, 425, 275);
        stage.setScene(scene);
        stage.setTitle("School Cancellations and Delays");
        stage.getIcons().add(new Image("bus.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static void aboutSCD() {
        String sInfo = "Program to enter\n";
        sInfo += "School cancellations\n";
        sInfo += "School delays\n";
        sInfo += "School early dismissals\n\n\n";
        sInfo += "Copyright: Michael Werner 2020\n";
        sInfo += "Icon made by Freepik from www.flaticon.com";
        
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) about.getDialogPane().getScene().getWindow();
        about.setTitle("About ...");
        stage.getIcons().add(new Image("bus.png"));
        about.setHeaderText(null);
        about.setContentText(sInfo);
        about.showAndWait();
    }
    
}