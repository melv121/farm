package farm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Farm {
    private int money;
    private double budget;
    private List<Field> fields;
    private List<Animal> animals;
    private Map<String, Integer> inventory;
    private int animalFeed; // Amount of animal feed available

    public Farm() {
        this.money = 500;
        this.budget = 500.0;
        this.fields = new ArrayList<>();
        this.animals = new ArrayList<>();
        this.inventory = new HashMap<>();
        this.animalFeed = 5; // Start with 5 units of feed

        // Crée 9 champs pour la grille 3x3
        for (int i = 0; i < 9; i++) {
            fields.add(new Field());
        }

        addToInventory("Wheat Seeds", 10);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int amount) {
        this.money += amount;
    }

    // Methods for Finance class
    public double getBudget() {
        return budget;
    }

    public void updateBudget(double amount) {
        this.budget += amount;
        // Update money as well to keep them synchronized
        this.money = (int)budget;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    // Animal feed methods
    public int getAnimalFeed() {
        return animalFeed;
    }

    public void addAnimalFeed(int amount) {
        animalFeed += amount;
    }

    // Purchase methods
    public boolean purchaseAnimal(String type, int price) {
        if (money >= price) {
            String productType;
            int productValue;
            int productionTime;
            String imagePath;

            switch (type) {
                case "Chicken":
                    productType = "egg";
                    productValue = 5;
                    productionTime = 1;
                    imagePath = "/images/chicken.png";
                    break;
                case "Cow":
                    productType = "milk";
                    productValue = 15;
                    productionTime = 2;
                    imagePath = "/images/cow.png";
                    break;
                case "Sheep":
                    productType = "wool";
                    productValue = 20;
                    productionTime = 3;
                    imagePath = "/images/sheep.png";
                    break;
                case "Pig":
                    productType = "meat";
                    productValue = 25;
                    productionTime = 4;
                    imagePath = "/images/pig.png";
                    break;
                case "Horse":
                    productType = "riding";
                    productValue = 30;
                    productionTime = 5;
                    imagePath = "/images/horse.png";
                    break;
                default:
                    return false;
            }

            setMoney(money - price);
            Animal newAnimal = new Animal(type, 100, productType, productValue, productionTime, imagePath);
            addAnimal(newAnimal);
            return true;
        }
        return false;
    }

    public boolean purchaseAnimalFeed(int amount, int pricePerUnit) {
        int totalPrice = amount * pricePerUnit;
        if (money >= totalPrice) {
            setMoney(money - totalPrice);
            addAnimalFeed(amount);
            return true;
        }
        return false;
    }

    // Animal management methods
    public boolean feedAnimal(int animalIndex) {
        if (animalIndex >= 0 && animalIndex < animals.size() && animalFeed > 0) {
            animals.get(animalIndex).feed();
            animalFeed--; // Consume one unit of feed
            return true;
        }
        return false;
    }

    // Feed all animals at once
    public int feedAllAnimals() {
        int fedCount = 0;
        for (int i = 0; i < animals.size() && animalFeed > 0; i++) {
            animals.get(i).feed();
            animalFeed--;
            fedCount++;
        }
        return fedCount;
    }

    public int collectAnimalProduct(int animalIndex) {
        if (animalIndex >= 0 && animalIndex < animals.size()) {
            Animal animal = animals.get(animalIndex);
            if (animal.hasProduct()) {
                int productValue = animal.collectProduct();
                addMoney(productValue);
                return productValue;
            }
        }
        return 0;
    }

    public boolean sellAnimal(int animalIndex) {
        if (animalIndex >= 0 && animalIndex < animals.size()) {
            Animal animal = animals.get(animalIndex);
            int basePrice = 0;
            switch (animal.getType()) {
                case "Chicken":
                    basePrice = 50;
                    break;
                case "Cow":
                    basePrice = 200;
                    break;
                case "Sheep":
                    basePrice = 150;
                    break;
                case "Pig":
                    basePrice = 300;
                    break;
                case "Horse":
                    basePrice = 400;
                    break;
                default:
                    basePrice = 100;
            }

            double healthPercent = animal.getHealth() / 100.0;
            int sellPrice = (int)(basePrice * 0.5 * healthPercent);

            addMoney(sellPrice);
            animals.remove(animalIndex);
            return true;
        }
        return false;
    }

    public boolean treatAnimal(int animalIndex) {
        if (animalIndex >= 0 && animalIndex < animals.size()) {
            Animal animal = animals.get(animalIndex);
            // Treatment costs $20
            if (money >= 20) {
                setMoney(money - 20);
                animal.setHealth(100);
                return true;
            }
        }
        return false;
    }

    public int getInventoryItemCount(String itemName) {
        return inventory.getOrDefault(itemName, 0);
    }

    public void addToInventory(String itemName, int quantity) {
        int currentQuantity = inventory.getOrDefault(itemName, 0);
        inventory.put(itemName, currentQuantity + quantity);
    }

    public boolean removeFromInventory(String itemName, int quantity) {
        int currentQuantity = inventory.getOrDefault(itemName, 0);
        if (currentQuantity >= quantity) {
            inventory.put(itemName, currentQuantity - quantity);
            return true;
        }
        return false;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void advanceDay() {
        // Process all animals
        for (Animal animal : animals) {
            animal.advanceDay();
        }

        // Process all fields
        for (Field field : fields) {
            if (field.hasCrop()) {
                field.updateCropGrowth();
            }
        }

        // Apply daily expenses via the finance system
        Finance finance = new Finance(this);
        finance.calculateDailyExpenses();
    }
}