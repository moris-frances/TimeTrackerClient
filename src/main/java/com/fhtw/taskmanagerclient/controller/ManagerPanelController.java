package com.fhtw.taskmanagerclient.controller;

import com.fhtw.taskmanagerclient.ClientApplication;
import com.fhtw.taskmanagerclient.helpers.DateTimeHelper;
import com.fhtw.taskmanagerclient.helpers.associateTaskListItem;
import com.fhtw.taskmanagerclient.helpers.helperMethods;
import com.fhtw.taskmanagerclient.model.Client;
import com.fhtw.taskmanagerclient.model.dto.GetAllAssociatesByManagerIdResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class ManagerPanelController implements Initializable {


    private ObservableList<associateTaskListItem> entriesList = FXCollections.observableArrayList();
    private Client client;

    private GetAllAssociatesByManagerIdResponse employees;
    @FXML
    private DatePicker dateViewDatePicker;

    @FXML
    private TableColumn<associateTaskListItem, String> employeeDateFromColumn;

    @FXML
    private TableColumn<associateTaskListItem, Double> employeeHoursSpentColumn;

    @FXML
    private TableColumn<associateTaskListItem, String> employeeNameTableColumn;

    @FXML
    private TableColumn<associateTaskListItem, String> employeeTaskColumn;

    @FXML
    private TableView<associateTaskListItem> entriesTableView;

    @FXML
    private Button monthlyViewButton;

    @FXML
    private Button updateEntriesButton;

    @FXML
    private Button weeklyViewButton;

    @FXML
    private Button updatePasswordButton;

    String currentStartDate = DateTimeHelper.getWeekStartDate();
    String currentEndDate = DateTimeHelper.getWeekEndDate();

    public ManagerPanelController(Client client) {
        this.client = client;
    }
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateEntriesList();
        employeeNameTableColumn.setCellValueFactory(new PropertyValueFactory<associateTaskListItem, String>("associateName"));
        employeeTaskColumn.setCellValueFactory(new PropertyValueFactory<associateTaskListItem, String>("employeeTask"));
        employeeDateFromColumn.setCellValueFactory(new PropertyValueFactory<associateTaskListItem, String>("employeeDateFrom"));
        employeeHoursSpentColumn.setCellValueFactory(new PropertyValueFactory<associateTaskListItem, Double>("employeeHoursSpent"));
        this.entriesTableView.setItems(entriesList);
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

    @FXML
    private void updateEntriesList() {
        try {
            employees = client.getAllManagersEmployeesTasks(currentStartDate, currentEndDate);

            entriesList = helperMethods.associateTaskListFactory(employees);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }



}
