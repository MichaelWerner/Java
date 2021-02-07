//http://tutorials.jenkov.com/javafx/index.html

package net._4werners.jenkov.tutorial;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MyFxApp extends Application {

    @Override
        public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My First JavaFX App");

        Label label = new Label("Hello World, JavaFX !");
        Scene scene = new Scene(label, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setX(50);
        primaryStage.setY(100);

        Stage secondStage = new Stage();
        
        secondStage.setWidth(300);
        secondStage.setHeight(100);
        secondStage.setX(100);
        secondStage.setY(200);
        secondStage.setTitle("second stage");
        //secondStage.initModality(Modality.APPLICATION_MODAL);  //blocks all other stages
        //stage.initModality(Modality.WINDOW_MODAL);     //blocks the owner of this stage
        //stage.initModality(Modality.NONE);             //doesn't block anything
        
        Label secondLabel = new Label("close me to continue !");
        Scene secondScene = new Scene(secondLabel, 400, 200);
        secondStage.setScene(secondScene);

        Stage thirdStage = new Stage();
        thirdStage.setWidth(300);
        thirdStage.setHeight(150);
        thirdStage.setX(200);
        thirdStage.setY(400);
        thirdStage.setTitle("third");
        thirdStage.initOwner(secondStage);
        thirdStage.initModality(Modality.WINDOW_MODAL);
        Label thirdLabel = new Label("you can click on the first stage\nbut not on the second");
        Scene thirdScene = new Scene(thirdLabel, 400, 200);
        thirdStage.setScene(thirdScene);
        		
        thirdStage.setOnCloseRequest((event) -> {
            System.out.println("Closing Stage 3");
        });
        
        //hiding = closing
        secondStage.setOnHiding((event) -> {
            System.out.println("Hiding Stage 2");
        });
        
        secondStage.setOnHidden((event) -> {
            System.out.println("Stage 2 hidden");
        });
        
        primaryStage.setOnShowing((event) -> {
            System.out.println("Showing Stage  1");
        });
        
        primaryStage.setOnShown((event) -> {
            System.out.println("Stage 1 Shown");
        });
        
        
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED,  (event) -> {
            System.out.println("Key pressed: " + event.toString());

            switch(event.getCode().getCode()) {
                case 27 : { // 27 = ESC key
                	System.out.println("Esc pressed");
                    primaryStage.close();
                    break;
                }
                case 10 : { // 10 = Return
                	System.out.println("Return pressed");
                    primaryStage.setWidth( primaryStage.getWidth() * 2);
                }
                default:  {
                    System.out.println("Unrecognized key");
                }
            }
        });        
        
        
        primaryStage.show();
        secondStage.show();  //waits until the stage is closed. Useful to execute code after that.
        thirdStage.show();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    

}
