/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication9;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author aboal
 */
public class FXMLDocumentController implements Initializable {

  
     @FXML
    private JFXButton btnPatient, btnDoctor, btnAbout,btnEmloyee;
 
   
    @FXML
    private Label lblAbout;

  

    //Constructor
    public FXMLDocumentController(){

    }

    //Method to open respective login pages on button press
    public void handleButtonClicks(javafx.event.ActionEvent ae)  {
        if (ae.getSource() == btnDoctor) {
            FxmlLoader.loadStage("doctor_login.fxml"); //loads doctor login page

((Node)(ae.getSource())).getScene().getWindow().hide(); //hides the main selection page
        } 
        else if (ae.getSource() == btnPatient) {
          
            FxmlLoader.loadStage("pationtlogin.fxml"); //loads patients login page
          
        ((Node)(ae.getSource())).getScene().getWindow().hide();
        
        } 
        else if (ae.getSource() == btnEmloyee) {
          
            FxmlLoader.loadStage("pationtlogin.fxml"); //loads patients login page
          
        ((Node)(ae.getSource())).getScene().getWindow().hide();
        
        }  
 else if (ae.getSource() == btnAbout) {
            showToast("Leads to Website!");
        }
    }

    private void closeApp() {
        System.exit(0);
    }

    //Method to show message on "About Us" button press
    private void showToast(String message) {
        lblAbout.setText(message);
        lblAbout.setTextFill(Color.TOMATO);
        lblAbout.setStyle("-fx-background-color: #fff; -fx-background-radius: 50px;");

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), lblAbout);  //show the message
        fadeIn.setToValue(1);
        fadeIn.setFromValue(0);
        fadeIn.play();

        fadeIn.setOnFinished(event -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));    //let the message stay
            pause.play();
            pause.setOnFinished(event2 -> {
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), lblAbout);    //fadeout the message
                fadeOut.setToValue(0);
                fadeOut.setFromValue(1);
                fadeOut.play();
            });
        });
    }


    //Init method to load the images into panes and call the method to control transitions
    @Override
    public void initialize(URL location, ResourceBundle resources) {

       
    }

   
    
}
