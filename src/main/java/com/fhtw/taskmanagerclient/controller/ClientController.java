package com.fhtw.taskmanagerclient.controller;

import com.fhtw.taskmanagerclient.model.Client;
import com.fhtw.taskmanagerclient.model.EntryModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    private Client client;

    @FXML
    private TextField dateFromTextField;

    @FXML
    private TextField dateToTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField taskTextField;

    @FXML
    private ListView<EntryModel> entriesListView;
    private ObservableList<EntryModel> entriesList = FXCollections.observableArrayList();
    public ClientController(Client client) {
        this.client = client;
    }

    @FXML
    protected void onSubmitButtonClick() throws IOException {

        EntryModel message = new EntryModel(Integer.parseInt(idTextField.getText()),
                nameTextField.getText(),
                taskTextField.getText(),
                dateFromTextField.getText(),
                dateToTextField.getText());
        //only for poc
        entriesList.add(message);
        this.client.sendMessage(message.toString());
        //receive update
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.entriesListView.setItems(entriesList);
    }
}