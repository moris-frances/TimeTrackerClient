package com.fhtw.taskmanagerclient;

import com.fhtw.taskmanagerclient.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class for the Task Manager client application.
 * It extends the javafx Application class and overrides the start method
 * to load the login view and set it as the scene for the primary stage.
 */
public class ClientApplication extends Application {
    /**
     * The method that is called when the application is launched.
     * It loads the login-view.fxml file and sets it as the scene for the primary stage.
     * It also sets the title of the stage to "Task Manager" and makes it visible.
     *
     * @param stage the primary stage of the application.
     * @throws IOException if there is a problem loading the login-view.fxml file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("login-view.fxml"));
        fxmlLoader.setControllerFactory(controllerClass -> new LoginController(stage));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Task Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    

}