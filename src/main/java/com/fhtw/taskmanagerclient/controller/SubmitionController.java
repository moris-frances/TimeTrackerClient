package com.fhtw.taskmanagerclient.controller;

import com.fhtw.taskmanagerclient.model.Client;
import com.fhtw.taskmanagerclient.model.dto.TaskDto;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.ParseException;


/**
 * The controller class for the submission view.
 */
public class SubmitionController{
    /**
     * The client object used to communicate with the server.
     */
    private Client client;
    /**
     * The stage of the submission view.
     */
    private Stage stage;

    /**
     * The duration text field.
     */
    @FXML
    private TextField durationTextField;

    /**
     * The task date date picker.
     */
    @FXML
    private DatePicker taskDateDatePicker;

    /**
     * The task text field.
     */
    @FXML
    private TextField taskTextField;

    /**
     * The constructor for the submission controller
     * @param client the client object used to communicate with the server
     * @param stage the stage of the submission view
     */
    public SubmitionController(Client client, Stage stage) {
        this.client = client;
        this.stage = stage;
    }

/**
 * Handles the click event for the submit button.
 * It sends the new task to the server and closes the submission window.
 * If any NumberFormatException is caught, it shows an alert message asking the user to enter a valid number of hours.
 * @throws IOException if an error occurs while communicating with the server.
 * @throws ParseException if the task date cannot be parsed.
 */
    @FXML
    protected void onSubmitButtonClick() throws IOException, ParseException {
        try {
            TaskDto task = new TaskDto(
                    taskTextField.getText(),
                    taskDateDatePicker.getValue().toString(),
                    Double.parseDouble(durationTextField.getText()));
            System.out.println(taskDateDatePicker.getValue().toString());
            this.client.addTask(task);
            stage.close();
        }catch(NumberFormatException n){

            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a valid number of hours!", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
        }
    }
}
