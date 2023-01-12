package com.fhtw.taskmanagerclient.controller;

import com.fhtw.taskmanagerclient.model.Client;
import com.fhtw.taskmanagerclient.model.dto.TaskDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class SubmitionController{

    private Client client;
    private Stage stage;
    @FXML
    private TextField durationTextField;
    @FXML
    private DatePicker taskDateDatePicker;

    @FXML
    private TextField taskTextField;
    public SubmitionController(Client client, Stage stage) {
        this.client = client;
        this.stage = stage;
    }

    @FXML
    protected void onSubmitButtonClick() throws IOException, ParseException {
        TaskDto task = new TaskDto(
                taskTextField.getText(),
                taskDateDatePicker.getValue().toString(),
                Double.parseDouble(durationTextField.getText()));

        this.client.addTask(task);
        stage.close();
    }


}
