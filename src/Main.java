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
            // Store the primary stage in MainController
            MainController.setPrimaryStage(primaryStage);

            // Create the farm
            Farm farm = new Farm();
            farm.setMoney(500);

            // Initialize game with farm
            MainController.initializeGame(farm);

            // Load the main menu with controller
            URL fxmlUrl = getClass().getClassLoader().getResource("views/MainMenu.fxml");
            if (fxmlUrl == null) {
                System.err.println("MainMenu.fxml not found in resources");
                return;
            }

            System.out.println("Loading MainMenu from: " + fxmlUrl);

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();

            // Get controller and set primaryStage
            MainMenuController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);

            Scene scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Farm Simulator");
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Failed to start application: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}