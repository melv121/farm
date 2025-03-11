import controller.MainController;
import controller.MainMenuController;
import farm.Farm;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Create MainController with primaryStage
            MainController mainController = new MainController(primaryStage);

            // Set up the farm with initial money
            Farm farm = mainController.getFarm();
            farm.setMoney(500);

            // Show the main menu
            mainController.showMainMenu();

        } catch (Exception e) {
            System.err.println("Failed to start application: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}