package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class FinanceController {
    @FXML
    private Label balanceLabel;

    @FXML
    private ListView<String> transactionHistory;

    @FXML
    private Button goBackButton;

    @FXML
    private void goBack() {
        System.out.println("Going back to the main menu...");
        // Logic for navigating back to the main menu (to be implemented)
    }

    public void updateBalance(double balance) {
        balanceLabel.setText(balance + " pièces");
    }

    public void addTransaction(String transaction) {
        transactionHistory.getItems().add(transaction);
    }
}
