//http://tutorials.jenkov.com/javafx/index.html

package net._4werners.jenkov.tutorial;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MyFxApp extends Application {

    @Override
        public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My First JavaFX App");

        Label label = new Label("Hello World, JavaFX !");
        Scene scene = new Scene(label, 400, 200);
        primaryStage.setScene(scene);

        Stage stage = new Stage();
        
        stage.setWidth(300);
        stage.setHeight(100);
        stage.initModality(Modality.APPLICATION_MODAL);  //blocks all other stages
        //stage.initModality(Modality.WINDOW_MODAL);     //blocks the owner of this stage
        //stage.initModality(Modality.NONE);             //doesn't block anything
        
        Label secondLabel = new Label("close me to continue !");
        Scene secondScene = new Scene(secondLabel, 400, 200);
        stage.setScene(secondScene);
        
        primaryStage.show();
        
        stage.showAndWait();  //waits until the stage is closed. Useful to execute code after that.
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    

}
