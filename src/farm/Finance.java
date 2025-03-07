package farm;

public class Finance {
    private Farm farm;

    public Finance(Farm farm) {
        this.farm = farm;
    }

    public void calculateDailyExpenses() {
        // Calculate daily expenses based on farm operations
        double animalUpkeep = calculateAnimalUpkeep();
        double fieldMaintenance = calculateFieldMaintenance();

        double totalExpenses = animalUpkeep + fieldMaintenance;

        // Update the farm's budget by subtracting expenses
        farm.updateBudget(-totalExpenses);
    }

    private double calculateAnimalUpkeep() {
        // Cost per animal per day
        double costPerAnimal = 5.0;
        return farm.getAnimals().size() * costPerAnimal;
    }

    private double calculateFieldMaintenance() {
        // Cost per field per day
        double costPerField = 2.0;
        return farm.getFields().size() * costPerField;
    }

    public double getCurrentBudget() {
        return farm.getBudget();
    }

    public void addIncome(double amount) {
        farm.updateBudget(amount);
    }

    public boolean canAfford(double cost) {
        return farm.getBudget() >= cost;
    }

    public void makePurchase(double cost) {
        if (canAfford(cost)) {
            farm.updateBudget(-cost);
        }
    }
}