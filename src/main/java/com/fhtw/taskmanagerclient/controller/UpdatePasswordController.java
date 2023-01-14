package com.fhtw.taskmanagerclient.controller;

import com.fhtw.taskmanagerclient.model.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdatePasswordController {
    Client client;

    Stage stage;
    public UpdatePasswordController(Client client, Stage stage){

        this.client = client;
        this.stage = stage;
    }


    @FXML
    private TextField newPasswordTextField;

    @FXML
    private TextField repeatNewPasswordTextField;

    @FXML
    private Button submitButton;

    @FXML
    void onSubmitButtonClick(ActionEvent event) throws IOException {
        if(this.client.updatePassword(newPasswordTextField.getText(), repeatNewPasswordTextField.getText()))
        {
            stage.close();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.WARNING, "Passwords do not match or Server is down! \n Try Again!", ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
                alert.close();
        }
    }


}
