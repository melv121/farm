
package farm;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<String, Double> itemsToBuy;
    private Map<String, Double> itemsToSell;

    public Store() {
        itemsToBuy = new HashMap<>();
        itemsToSell = new HashMap<>();

        // Crops seeds
        itemsToBuy.put("Graines de blé", 10.0);
        itemsToBuy.put("Graines de maïs", 15.0);
        itemsToBuy.put("Graines de carottes", 20.0);

        // Animals with prices between 200-400
        itemsToBuy.put("Vache", 200.0);
        itemsToBuy.put("Mouton", 250.0);
        itemsToBuy.put("Cochon", 300.0);
        itemsToBuy.put("Cheval", 400.0);
        itemsToBuy.put("Poulet", 50.0);

        // Animal feed
        itemsToBuy.put("Nourriture animale", 25.0);

        // Selling prices
        itemsToSell.put("Blé", 12.0);
        itemsToSell.put("Maïs", 18.0);
        itemsToSell.put("Carottes", 25.0);
        itemsToSell.put("Lait", 30.0);
        itemsToSell.put("Œufs", 10.0);
        itemsToSell.put("Laine", 35.0);
    }

    public double getBuyPrice(String item) {
        return itemsToBuy.getOrDefault(item, -1.0);
    }

    public double getSellPrice(String item) {
        return itemsToSell.getOrDefault(item, -1.0);
    }

    public Map<String, Double> getItemsToBuy() {
        return itemsToBuy;
    }

    public Map<String, Double> getItemsToSell() {
        return itemsToSell;
    }
}
