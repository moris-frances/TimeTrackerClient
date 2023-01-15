package com.fhtw.taskmanagerclient.controller;
import com.fhtw.taskmanagerclient.ClientApplication;
import com.fhtw.taskmanagerclient.model.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller for the login view. It handles the interaction between the view and the model.
 * It implements the Initializable interface to initialize the client object and the signInButtonClick
 * method to handle the sign-in button click event.
 */
public class LoginController implements Initializable{

    private Stage stage;
    private Client client;
    /**
     * The text field for the username.
     */
    @FXML
    private TextField usernameTextField;

    /**
     * The password field for the password.
     */
    @FXML
    private TextField passwordPasswordField;

    /**
     * The sign-in button.
     */
    @FXML
    private Button signInButton;

    /**
     * Creates a new instance of the login controller.
     *
     * @param stage the primary stage of the application.
     */

    public LoginController(Stage stage){
        this.stage = stage;
    }

    /**
     * Handles the sign-in button click event. It checks if the login is successful and based on the role of the user
     * it loads the appropriate view and sets it as the scene for the primary stage.
     *
     * @param event the action event of the button click.
     * @throws IOException if there is a problem loading the fxml file.
     */
    @FXML
    void signInButtonClick(ActionEvent event) throws IOException {
        if(client.login(usernameTextField.getText(), passwordPasswordField.getText()))
        {
            if(client.getUserRole().equals("manager")){
                FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("manager-view.fxml"));
                fxmlLoader.setControllerFactory(controllerClass -> new ManagerPanelController(client));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Manager Panel");
                stage.setScene(scene);
                stage.show();
                return;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("client-view.fxml"));
            fxmlLoader.setControllerFactory(controllerClass -> new TaskManagerController(client));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Task Manager");
            stage.setScene(scene);
            stage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong Credentials \n (or internal Server Error)", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
