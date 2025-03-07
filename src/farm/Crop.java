package farm;

public class Crop {
    private String type;
    private int growthStage;
    private int growthRate;
    private int maxGrowth;
    private int yield;
    private int value;

    public Crop(String type) {
        this.type = type;
        this.growthStage = 0;

        // Valeurs par défaut - peuvent être personnalisées selon le type de culture
        if ("Wheat".equals(type)) {
            // 25% de croissance par seconde = 4 secondes pour atteindre 100%
            this.growthRate = 25;
            this.maxGrowth = 100;
            this.yield = 3;
            this.value = 20;
        } else {
            this.growthRate = 25;
            this.maxGrowth = 100;
            this.yield = 1;
            this.value = 10;
        }
    }

    public void grow() {
        growthStage = Math.min(growthStage + growthRate, maxGrowth);
    }

    public boolean isReadyToHarvest() {
        return growthStage >= maxGrowth;
    }

    public String getType() {
        return type;
    }

    public int getGrowthStage() {
        return growthStage;
    }

    public int getYield() {
        return yield;
    }

    public int getValue() {
        return value;
    }
}