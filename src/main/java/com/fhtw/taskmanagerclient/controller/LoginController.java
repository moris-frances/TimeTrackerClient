package com.fhtw.taskmanagerclient.controller;
import com.fhtw.taskmanagerclient.ClientApplication;
import com.fhtw.taskmanagerclient.model.Client;
import com.fhtw.taskmanagerclient.model.EntryModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    private Stage stage;
    private Client client;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button signInButton;

    @FXML
    private TextField usernameTextField;

    public LoginController(Stage stage){
        this.stage = stage;
    }

    @FXML
    void signInButtonClick(ActionEvent event) throws IOException {
        if(client.loginRequest(usernameTextField.getText(), passwordTextField.getText()))
        {
            FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("client-view.fxml"));
            fxmlLoader.setControllerFactory(controllerClass -> new ClientController(client));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Task Manager");
            stage.setScene(scene);
            stage.show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            client =  new Client("localhost", 8888);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
