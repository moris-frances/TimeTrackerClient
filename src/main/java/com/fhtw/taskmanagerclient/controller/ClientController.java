package com.fhtw.taskmanagerclient.controller;

import com.fhtw.taskmanagerclient.ClientApplication;
import com.fhtw.taskmanagerclient.helpers.DateTimeHelper;
import com.fhtw.taskmanagerclient.model.Client;
import com.fhtw.taskmanagerclient.model.dto.GetAssociateTasksRequest;
import com.fhtw.taskmanagerclient.model.dto.GetAssociateTasksResponse;
import com.fhtw.taskmanagerclient.model.dto.TaskDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    private Client client;

    @FXML
    private Button newEntryButton;

    @FXML
    private ListView<TaskDto> entriesListView;

    private GetAssociateTasksResponse tasks;
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

        String currentWeekStartDate = DateTimeHelper.getWeekStartDate();
        String currentWeekEndDate = DateTimeHelper.getWeekEndDate();

        try {
            tasks = client.getTasksInAnInterval(currentWeekStartDate, currentWeekEndDate, client.getToken());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            entriesList.addAll(tasks.getTasks());
        }catch (NullPointerException n){
            System.out.println("nothing for this week");
        }

        this.entriesListView.setItems(entriesList);

    }
}