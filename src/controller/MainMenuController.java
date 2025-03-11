package controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MainMenuController {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void goToFarm() {
        try {
            MainController.getInstance().showFarm();
        } catch (Exception e) {
            System.err.println("Farm.fxml not found in resources");
            e.printStackTrace();
        }
    }

    @FXML
    private void goToStore() {
        try {
            MainController.getInstance().showStore();
        } catch (Exception e) {
            System.err.println("Store.fxml not found in resources");
            e.printStackTrace();
        }
    }

    @FXML
    private void advanceDay() {
        MainController.getInstance().advanceDay();
        System.out.println("Advancing the day...");
    }

    @FXML
    private void exitGame() {
        primaryStage.close();
    }
}