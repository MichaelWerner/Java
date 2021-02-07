//http://tutorials.jenkov.com/javafx/index.html

package net._4werners.jenkov.tutorial;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MyFxApp extends Application {

    @Override
        public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My First JavaFX App");
        
        Label label = new Label("Hello World, JavaFX !");
        Scene scene = new Scene(label, 400, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    

}
