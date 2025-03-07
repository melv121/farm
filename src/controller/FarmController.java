package controller;

import farm.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.List;

public class FarmController {

    @FXML
    private GridPane fieldGrid;

    @FXML
    private Label moneyLabel;

    private Farm farm;
    private Field selectedField;
    private final int GRID_SIZE = 3; // 3x3 grid for fields
    private Timeline growthTimeline;

    @FXML
    public void initialize() {
        farm = MainController.getFarm();
        updateMoneyDisplay();
        setupFieldGrid();
        startGrowthTimer();
    }

    private void startGrowthTimer() {
        // Arrête le timer s'il existait déjà
        if (growthTimeline != null) {
            growthTimeline.stop();
        }

        // Crée un nouveau timer qui s'exécute chaque seconde
        growthTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    // Met à jour la croissance de toutes les cultures
                    for (Field field : farm.getFields()) {
                        if (field.hasCrop()) {
                            field.updateCropGrowth();
                        }
                    }
                    // Rafraîchit l'affichage
                    refreshFieldDisplay();
                })
        );

        growthTimeline.setCycleCount(Timeline.INDEFINITE);
        growthTimeline.play();
    }

    private void refreshFieldDisplay() {
        Platform.runLater(this::setupFieldGrid);
    }

    private void updateMoneyDisplay() {
        moneyLabel.setText("Argent: " + farm.getMoney() + " pièces");
    }

    private void setupFieldGrid() {
        fieldGrid.getChildren().clear();

        List<Field> fields = farm.getFields();

        // Create a visual representation for each field
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                final int index = i * GRID_SIZE + j;

                VBox fieldBox = new VBox(5);
                fieldBox.setAlignment(Pos.CENTER);
                fieldBox.setPrefSize(80, 80);
                fieldBox.setStyle("-fx-border-color: brown; -fx-background-color: #C19A6B;");

                Field field = index < fields.size() ? fields.get(index) : null;

                if (field != null) {
                    String displayText = field.hasCrop() ?
                            field.getCrop().getType() + "\n" +
                                    "État: " + field.getCrop().getGrowthStage() + "%" : "Champ vide";

                    Label fieldLabel = new Label(displayText);
                    fieldBox.getChildren().add(fieldLabel);

                    // Change color based on growth stage
                    if (field.hasCrop()) {
                        int growth = field.getCrop().getGrowthStage();
                        if (growth >= 100) {
                            // Ready to harvest (golden)
                            fieldBox.setStyle("-fx-border-color: brown; -fx-background-color: #FFD700;");
                        } else if (growth > 60) {
                            // Almost ready (light green)
                            fieldBox.setStyle("-fx-border-color: brown; -fx-background-color: #90EE90;");
                        } else if (growth > 30) {
                            // Growing (green)
                            fieldBox.setStyle("-fx-border-color: brown; -fx-background-color: #32CD32;");
                        } else {
                            // Just planted (light brown)
                            fieldBox.setStyle("-fx-border-color: brown; -fx-background-color: #D2B48C;");
                        }
                    }

                    // Set event handler to select this field
                    final VBox finalFieldBox = fieldBox;
                    fieldBox.setOnMouseClicked(e -> selectField(field, finalFieldBox));
                }

                fieldGrid.add(fieldBox, j, i);
            }
        }
    }

    private void selectField(Field field, VBox fieldBox) {
        // Clear previous selection
        fieldGrid.getChildren().forEach(node -> {
            if (node instanceof VBox) {
                VBox box = (VBox) node;
                // Get the previous style but remove the blue border
                String style = box.getStyle();
                if (style.contains("-fx-border-color: blue")) {
                    style = style.replace("-fx-border-color: blue", "-fx-border-color: brown");
                    box.setStyle(style);
                }
            }
        });

        // Mark new selection with blue border, but keep background color
        String currentStyle = fieldBox.getStyle();
        String newStyle = currentStyle.replace("-fx-border-color: brown", "-fx-border-color: blue");
        fieldBox.setStyle(newStyle);
        selectedField = field;
    }

    @FXML
    public void plantCrop() {
        if (selectedField != null && !selectedField.hasCrop()) {
            // Pour simplifier, on plante juste du blé
            // Normalement, vérifie si l'utilisateur a des graines
            // Mais comme c'est pour le debug:
            selectedField.plantCrop(new Crop("Wheat"));
            // farm.removeFromInventory("Wheat Seeds", 1); // Décommenté quand l'inventaire sera implémenté
            setupFieldGrid(); // Rafraîchit la grille
        }
    }

    @FXML
    public void harvestCrop() {
        if (selectedField != null && selectedField.hasCrop() && selectedField.getCrop().isReadyToHarvest()) {
            Crop crop = selectedField.getCrop();
            // Ajoute les éléments récoltés à l'inventaire
            farm.addToInventory(crop.getType(), crop.getYield());
            // Ajoute de l'argent
            farm.addMoney(crop.getValue());
            // Vide le champ
            selectedField.harvestCrop();

            // Met à jour les affichages
            updateMoneyDisplay();
            setupFieldGrid();
        } else if (selectedField != null && selectedField.hasCrop()) {
            System.out.println("La culture n'est pas encore prête à être récoltée");
        }
    }

    @FXML
    public void feedAnimals() {
        // À implémenter plus tard
    }

    @FXML
    public void collectProducts() {
        // À implémenter plus tard
    }

    @FXML
    public void goBack() {
        // Arrête le timer quand on quitte la vue
        if (growthTimeline != null) {
            growthTimeline.stop();
        }

        try {
            MainController.showMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}