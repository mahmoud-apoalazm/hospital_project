/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication9;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author aboal
 */
public class JavaFXApplication9 extends Application {
    
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); //load the main page view
            primaryStage.setTitle("Welcome");
            primaryStage.setScene(new Scene(root, 1200, 700));

    //        primaryStage.getIcons().add(new Image("icon.png"));
           primaryStage.getScene().getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());

            

            primaryStage.show();
            primaryStage.setMaximized(false);
            primaryStage.setResizable(false);
            primaryStage.sizeToScene();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main (String[]args){
            launch(args);
        }
}