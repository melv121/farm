package controller;

import farm.Farm;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
    private static Farm farm;
    private static Stage primaryStage;

    public static void initializeGame(Farm newFarm) {
        farm = newFarm;
    }

    public static void setFarm(Farm newFarm) {
        farm = newFarm;
    }

    public static Farm getFarm() {
        return farm;
    }

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void showMainMenu() throws Exception {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/views/MainMenu.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 800, 600));
    }

    public static void showStore() throws Exception {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/views/Store.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 800, 600));
    }

    public static void showFarm() throws Exception {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/views/Farm.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 800, 600));
    }

    public static void advanceDay() {


        if (farm != null) {

            for (int i = 0; i < farm.getFields().size(); i++) {
                if (farm.getFields().get(i).hasCrop()) {
                    farm.getFields().get(i).updateCropGrowth();
                }
            }



            System.out.println("Un jour a passé dans le jeu");
        }
    }
}