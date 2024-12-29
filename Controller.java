package com.example.loginapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {
    @FXML //bind the cancelBtn to the Button defined in the fxml file/scenebuilder
    private Button cancelBtn;
    @FXML
    private Label loginErrorLbl;
    @FXML
    private TextField usernameTxt;
    @FXML
    private PasswordField passwordTxt;

    public void cancelBtnOnAction(ActionEvent e){
        Stage stage = (Stage) cancelBtn.getScene().getWindow(); // (stage) is casting the .getWindow() as a window (could be dialogue box etc)
        stage.close();
    }

    public void loginBtnOnAction(ActionEvent e){
        if(usernameTxt.getText().isBlank() == false && passwordTxt.getText().isBlank() == false){
            //loginErrorLbl.setText("LOGIN UNSUCCESSFUL. Please try again");
            validateLogin();

        }
        else{
            loginErrorLbl.setText("Enter a username and password");
        }
    }

    public void validateLogin(){
        DatabaseConnector conectNow = new DatabaseConnector();
        Connection connectDB = conectNow.getConnection();

        String verifyLogin = "select count(1) from TetCorp.UserAccounts where userName = '" + usernameTxt.getText() + "' and password = '" + passwordTxt.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin); //allows you to execute a query i.e verifyLogin

            while(queryResult.next()){
                //if there is a matching record let the user in
                if(queryResult.getInt(1) == 1){
                    loginErrorLbl.setText("Welcome!");
                }
                else{
                    loginErrorLbl.setText("Wrong credentials. Try again.");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();

        }

    }

}
