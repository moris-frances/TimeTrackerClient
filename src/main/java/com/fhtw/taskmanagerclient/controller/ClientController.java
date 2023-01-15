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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
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
    private TableView<TaskDto> entriesTableView;
    @FXML private TableColumn<TaskDto, String> employeeTaskColumn;
    @FXML private TableColumn<TaskDto, String> employeeDateFromColumn;
    @FXML private TableColumn<TaskDto, Double> employeeHoursSpentColumn;
    @FXML
    private ListView<TaskDto> entriesListView;
    private GetAssociateTasksResponse tasks;

    private ObservableList<TaskDto> entriesList = FXCollections.observableArrayList();
    @FXML
    private Button monthlyViewButton;

    @FXML
    private Button weeklyViewButton;

    @FXML
    private DatePicker dateViewDatePicker;


    @FXML
    void onDateViewDatePickerChanged() {
        currentStartDate = DateTimeHelper.getFormattedDateString(dateViewDatePicker.getValue());
        currentEndDate = DateTimeHelper.getFormattedDateString(dateViewDatePicker.getValue());
        updateEntriesList();
    }

    @FXML
    void onMonthlyViewButtonButtonClick(ActionEvent event) {
        currentStartDate = DateTimeHelper.getMonthStartDate();
        currentEndDate = DateTimeHelper.getMonthEndDate();
        updateEntriesList();
    }


    @FXML
    void onWeeklyViewButtonButtonClick(ActionEvent event) {
        currentStartDate = DateTimeHelper.getWeekStartDate();
        currentEndDate = DateTimeHelper.getWeekEndDate();
        updateEntriesList();
    }
    public ClientController(Client client) {
        this.client = client;
    }


    String currentStartDate = DateTimeHelper.getWeekStartDate();
    String currentEndDate = DateTimeHelper.getWeekEndDate();



    @FXML
    void onNewEntryButtonClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("new_entry-view.fxml"));
        fxmlLoader.setControllerFactory(controllerClass -> new SubmitionController(client, stage));
        //updates entries when closed
        stage.setOnHidden(e->{this.updateEntriesList();});
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("New Entry Submission");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onUpdatePasswordButtonClick() throws IOException {
        //UpdatePasswordController
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("update_password-view.fxml"));
        fxmlLoader.setControllerFactory(controllerClass -> new UpdatePasswordController(client, stage));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Update Password");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateEntriesList();
        employeeTaskColumn.setCellValueFactory(new PropertyValueFactory<TaskDto, String>("employeeTask"));
        employeeDateFromColumn.setCellValueFactory(new PropertyValueFactory<TaskDto, String>("employeeDateFrom"));
        employeeHoursSpentColumn.setCellValueFactory(new PropertyValueFactory<TaskDto, Double>("employeeHoursSpent"));
        this.entriesTableView.setItems(entriesList);
    }


    private void updateEntriesList(){
        try {
            tasks = client.getTasksInAnInterval(currentStartDate, currentEndDate);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            entriesList.setAll(tasks.getTasks());
        }catch (NullPointerException n){
            System.out.println("No Entries in this period");
        }

    }
}