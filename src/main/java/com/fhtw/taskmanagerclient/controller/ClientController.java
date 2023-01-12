package com.fhtw.taskmanagerclient.controller;

import com.fhtw.taskmanagerclient.ClientApplication;
import com.fhtw.taskmanagerclient.model.Client;
import com.fhtw.taskmanagerclient.model.dto.TaskDto;
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

public class ClientController implements Initializable {

    private Client client;

    @FXML
    private Button newEntryButton;

    @FXML
    private ListView<TaskDto> entriesListView;
    private ObservableList<TaskDto> entriesList = FXCollections.observableArrayList();
    public ClientController(Client client) {
        this.client = client;
    }





    @FXML
    void onNewEntryButtonClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("new_entry-view.fxml"));
        fxmlLoader.setControllerFactory(controllerClass -> new SubmitionController(client, stage));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("New Entry Submission");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.entriesListView.setItems(entriesList);
    }
}