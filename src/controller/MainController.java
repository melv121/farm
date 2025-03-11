package controller;

import farm.Farm;
import farm.Field;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainController {

    private static MainController instance;
    private Farm farm;
    private Stage primaryStage;

    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.farm = new Farm();
        instance = this;
    }

    public static MainController getInstance() {
        if (instance == null) {
            // Create a placeholder instance if needed
            instance = new MainController(new Stage());
        }
        return instance;
    }

    public Farm getFarm() {
        if (farm == null) {
            farm = new Farm();
        }
        return farm;
    }

    public void showMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/MainMenu.fxml"));
            Parent root = loader.load();

            MainMenuController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Farm Simulator - Main Menu");
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error loading main menu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showFarm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Farm.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Farm Simulator - Your Farm");
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error loading farm view: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showStore() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Store.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Farm Simulator - Store");
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error loading store view: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showFieldDetail(Field field) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FieldDetail.fxml"));
            Parent root = loader.load();

            // For now, we'll skip the controller setup until the FieldDetailController is created
            // Just show the window without setting up the field data
            Stage detailStage = new Stage();
            detailStage.setScene(new Scene(root));
            detailStage.setTitle("Field Details");
            detailStage.show();
        } catch (IOException e) {
            System.err.println("Error loading field detail: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void advanceDay() {
        farm.advanceDay();
        System.out.println("A new day has dawned on your farm!");
    }
}