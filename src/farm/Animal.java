package farm;

public class Animal {
    private String type;
    private int health;
    private String productType;
    private int productValue;
    private int productionTime;
    private String imagePath;
    private int daysUntilProduction;
    private boolean hasProduct;

    public Animal(String type, int health, String productType,
                  int productValue, int productionTime, String imagePath) {
        this.type = type;
        this.health = health;
        this.productType = productType;
        this.productValue = productValue;
        this.productionTime = productionTime;
        this.imagePath = imagePath;
        this.daysUntilProduction = productionTime;
        this.hasProduct = false;
    }

    public String getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getProductType() {
        return productType;
    }

    public int getProductValue() {
        return productValue;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean hasProduct() {
        return hasProduct;
    }

    public void feed() {
        health = Math.min(100, health + 10);
    }

    public void advanceDay() {
        if (!hasProduct) {
            daysUntilProduction--;
            if (daysUntilProduction <= 0) {
                hasProduct = true;
                daysUntilProduction = productionTime;
            }
        }
        health = Math.max(0, health - 5); // L'animal perd de la santé chaque jour
    }

    public int collectProduct() {
        if (hasProduct) {
            hasProduct = false;
            return productValue;
        }
        return 0;
    }
}