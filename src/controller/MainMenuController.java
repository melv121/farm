package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void goToFarm() {
        try {
            MainController.showFarm();
        } catch (Exception e) {
            System.err.println("Farm.fxml not found in resources");
            e.printStackTrace();
        }
    }

    @FXML
    private void goToStore() {
        try {
            MainController.showStore();
        } catch (Exception e) {
            System.err.println("Store.fxml not found in resources");
            e.printStackTrace();
        }
    }

    @FXML
    private void advanceDay() {
        MainController.advanceDay();
        System.out.println("Advancing the day...");
    }


    @FXML
    private void exitGame() {
        primaryStage.close();
    }
}