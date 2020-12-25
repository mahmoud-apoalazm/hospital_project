/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication9;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author aboal
 */
public class PationtloginController implements Initializable {

 private Connection con = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @FXML
    private JFXTextField txtUsername;
    @FXML
    private Label lblErrors;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnMain, btnSignup, btnSignin;


    ///Constructor call on opening page
    public PationtloginController() throws ClassNotFoundException {
        try {
            con = DBConnection.getConnection();  //establishing connection to database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void closeApp() throws SQLException {
        if (con != null) {
            con.close();
        }
        System.exit(0);
    }

    @FXML
    public void handleButtonClicks(javafx.event.ActionEvent ae) throws SQLException {
        if (ae.getSource() == btnMain) {  ////button to go back to main page
//            if (con != null) {
//                con.close();
//            }
            FxmlLoader.loadStage("FXMLDocument.fxml");
            ((Node)(ae.getSource())).getScene().getWindow().hide();
        }
        else if (ae.getSource() == btnSignup) {   ///button to go to signup page on being new user
//            if (con != null) {
//                con.close();
//            }
            FxmlLoader.loadStage("pationregister.fxml");
            ((Node)(ae.getSource())).getScene().getWindow().hide();
        }
        else if (ae.getSource() == btnSignin) {   ////button to check signin and go to patient's dashboard
            if (logIn().equals("Success")) {
//                if (con != null) {
//                    con.close();
//                }
                FxmlLoader.loadStage("pationdashboard.fxml");
                ((Node) (ae.getSource())).getScene().getWindow().hide();
            }
        }
    }

    //method to check login
    private String logIn() throws SQLException {

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        String sql = "SELECT * FROM patients Where username = ? and password = ?";

        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                lblErrors.setTextFill(Color.TOMATO);
                lblErrors.setText("Enter Correct Username/Password");
                txtUsername.clear();
                txtPassword.clear();
                return "Error";
            } else {
                lblErrors.setTextFill(Color.GREEN);
                lblErrors.setText("Login Successful..Redirecting..");
                SessionSaver.setUsername(txtUsername.getText());    ////save the successful login's username
                return "Success";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Exception";
        }finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }


    public void initialize(URL location, ResourceBundle resources) {
        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");  //let user know error in establishing connection to database
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");  //let the user know connection to database is established
        }

        ///SignIn on pressing enter on Keyboard
        txtPassword.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    if (logIn().equals("Success")) {
                        if (con != null) {
                            con.close();
                        }
                        FxmlLoader.loadStage("pationdashboard.fxml");
                        ((Node) (e.getSource())).getScene().getWindow().hide();
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        //
    }
}
