package controller;

import farm.Animal;
import farm.Farm;
import farm.Field;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class StoreController {
    private Farm farm;

    @FXML
    private Label moneyLabel;

    @FXML
    private ListView<String> storeItemsListView;

    public void initialize() {
        farm = MainController.getInstance().getFarm();
        updateMoneyDisplay();

        // Populate store items
        storeItemsListView.getItems().addAll(
                "Chicken - $50",
                "Cow - $200",
                "Sheep - $150",
                "Wheat Seeds - $10",
                "Corn Seeds - $15",
                "Additional Field - $100"
        );
    }

    private void updateMoneyDisplay() {
        moneyLabel.setText("Money: $" + farm.getMoney());
    }

    @FXML
    public void handlePurchase(ActionEvent event) {
        String selectedItem = storeItemsListView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;

        if (selectedItem.startsWith("Chicken")) {
            purchaseAnimal("Chicken", 50, "egg", 5, 1, "/images/chicken.png");
        } else if (selectedItem.startsWith("Cow")) {
            purchaseAnimal("Cow", 200, "milk", 15, 2, "/images/cow.png");
        } else if (selectedItem.startsWith("Sheep")) {
            purchaseAnimal("Sheep", 150, "wool", 20, 3, "/images/sheep.png");
        } else if (selectedItem.startsWith("Wheat Seeds")) {
            purchaseSeed("Wheat", 10);
        } else if (selectedItem.startsWith("Corn Seeds")) {
            purchaseSeed("Corn", 15);
        } else if (selectedItem.startsWith("Additional Field")) {
            purchaseField(100);
        }
    }

    private void purchaseAnimal(String name, int price, String product,
                                int productValue, int productionTime, String imagePath) {
        if (farm.getMoney() >= price) {
            farm.setMoney(farm.getMoney() - price);
            Animal newAnimal = new Animal(name, 100, product, productValue, productionTime, imagePath);
            farm.addAnimal(newAnimal);
            updateMoneyDisplay();
        } else {
            // Show insufficient funds message
            System.out.println("Fonds insuffisants pour acheter " + name);
        }
    }

    private void purchaseSeed(String cropType, int price) {
        if (farm.getMoney() >= price) {
            farm.setMoney(farm.getMoney() - price);
            farm.addToInventory(cropType + " Seeds", 1);
            updateMoneyDisplay();
        } else {
            // Show insufficient funds message
            System.out.println("Fonds insuffisants pour acheter des graines de " + cropType);
        }
    }

    private void purchaseField(int price) {
        if (farm.getMoney() >= price) {
            farm.setMoney(farm.getMoney() - price);
            farm.addField(new Field());
            updateMoneyDisplay();
        } else {
            // Show insufficient funds message
            System.out.println("Fonds insuffisants pour acheter un nouveau champ");
        }
    }

    @FXML
    public void handleBack(ActionEvent event) {
        try {
            MainController.getInstance().showMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}