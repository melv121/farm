package farm;

public class Field {
    private Crop crop;

    public Field() {
        this.crop = null;
    }

    public boolean hasCrop() {
        return crop != null;
    }

    public Crop getCrop() {
        return crop;
    }

    public void plantCrop(Crop crop) {
        this.crop = crop;
    }

    public Crop harvestCrop() {
        Crop harvestedCrop = this.crop;
        this.crop = null;
        return harvestedCrop;
    }

    public void updateCropGrowth() {
        if (crop != null) {
            crop.grow();
        }
    }
}