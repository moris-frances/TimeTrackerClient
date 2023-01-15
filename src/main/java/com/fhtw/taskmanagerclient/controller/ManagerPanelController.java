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

/**
 * The ManagerPanelController class is the controller for the Manager Panel view.
 * It is responsible for handling the events and logic of the view and communicating with the client model.
 *
 */
public class ManagerPanelController implements Initializable {
    /**
     * The entriesList variable is an observable list that holds the tasks of the associates
     */
    private ObservableList<associateTaskListItem> entriesList = FXCollections.observableArrayList();
    /**
     * The client variable is the client model
     */
    private Client client;

    /**
     * The employees variable is the response from the getAllAssociatesByManagerId method
     */
    private GetAllAssociatesByManagerIdResponse employees;
    /**
     * The date picker for the update entries single-date server request.
     */
    @FXML
    private DatePicker dateViewDatePicker;

    @FXML private TableColumn<associateTaskListItem, String> employeeTaskColumn;
    /**
     * The date from column of the table view.
     */
    @FXML private TableColumn<associateTaskListItem, String> employeeDateFromColumn;
    /**
     * The hours spent column of the table view.
     */
    @FXML private TableColumn<associateTaskListItem, Double> employeeHoursSpentColumn;
    /**
     * The employee name column of the table view.
     */
    @FXML
    private TableColumn<associateTaskListItem, String> employeeNameTableColumn;
    /**
     * The table view object, representing the table where entries are displayed.
     */
    @FXML
    private TableView<associateTaskListItem> entriesTableView;
    /**
     * The monthly view button.
     */
    @FXML
    private Button monthlyViewButton;

    /**
     * The weekly view button.
     */
    @FXML
    private Button weeklyViewButton;

    /**
     * The update entries button.
     */
    @FXML
    private Button updateEntriesButton;
    /**
     * The Update Password button.
     */
    @FXML
    private Button updatePasswordButton;

    /**
     * The current start date.
     */
    String currentStartDate = DateTimeHelper.getWeekStartDate();
    /**
     * The current end date.
     */
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
    /**
     * Handles the click event for the monthly view button.
     * It updates the current start and end date with the start and end of the current month
     * and updates the entries list.
     * @param event the click event.
     */
    @FXML
    void onMonthlyViewButtonButtonClick(ActionEvent event) {
        currentStartDate = DateTimeHelper.getMonthStartDate();
        currentEndDate = DateTimeHelper.getMonthEndDate();
        updateEntriesList();
    }

    /**
     * Handles the click event for the weekly view button.
     * It updates the current start and end date with the start and end of the current week
     * and updates the entries list.
     * @param event the click event.
     */
    @FXML
    void onWeeklyViewButtonButtonClick(ActionEvent event) {
        currentStartDate = DateTimeHelper.getWeekStartDate();
        currentEndDate = DateTimeHelper.getWeekEndDate();
        updateEntriesList();
    }
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded. It sets up the TableView with the
     * appropriate columns and calls the updateEntriesList method to populate
     * the TableView with data.
     *
     * @param location
     *            the location used to resolve relative paths for the root
     *            object, or null if the location is not known
     * @param resources
     *            the resources used to localize the root object, or null if
     *            the root object was not localized
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateEntriesList();
        employeeNameTableColumn.setCellValueFactory(new PropertyValueFactory<associateTaskListItem, String>("associateName"));
        employeeTaskColumn.setCellValueFactory(new PropertyValueFactory<associateTaskListItem, String>("employeeTask"));
        employeeDateFromColumn.setCellValueFactory(new PropertyValueFactory<associateTaskListItem, String>("employeeDateFrom"));
        employeeHoursSpentColumn.setCellValueFactory(new PropertyValueFactory<associateTaskListItem, Double>("employeeHoursSpent"));
        this.entriesTableView.setItems(entriesList);
    }
    /**
     * This method is called when the update password button is clicked. It opens a new stage
     * for the user to update their password

     * @throws IOException
     *            if there is an error loading the update password view
     */
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
    /**
     * This method is called when the update entries button is clicked.
     * It updates the entriesList field and populates the TableView with the data
     *
     */
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
