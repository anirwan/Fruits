import java.util.HashMap;
import java.util.Map;


/**
 * Created by Anirwan on 17/02/2016.
 */


public class FruitSelector {

    FruitCombination combinations = new FruitCombination();
    private String[] fruits = {"banana",
            "kiwi",
            "mango",
            "papaya",
            "pineapple",
            "apple",
            "lemon",
            "coconut",
            "orange",
            "grapefruit",
            "pear",
            "jackfruit",
            "watermelon"};
    private String[] prices = {"32.00",
            "41.00",
            "97.00",
            "254.00",
            "399.00",
            "59.00",
            "70.00",
            "155.00",
            "73.00",
            "128.00",
            "37.00",
            "1100.00",
            "500.00"};
    private double cashInHand = 400.00;
    private double remaining = 400.00;
    private double currentTotal = 0;
    private Map<String, String> fruitPrices = new HashMap<String, String>();
    private Map<String, String> fruitsBought = new HashMap<String, String>();
    private Map<String, String> fruitPricesBackup = new HashMap<String, String>();

    public FruitSelector(int scenario) {

        int length = fruits.length;

        if (scenario == 1) {
            length = 5;
            cashInHand = 500.00;
            remaining = 500.00;
        }

        for (int i = 0; i < length; i++) {
            fruitPrices.put(fruits[i], prices[i]);
            fruitsBought.put(fruits[i], "0");
        }

        fruitPricesBackup.putAll(fruitPrices);
    }

    public void compute() {
        double highestPriceFruit = 0;
        String fruit = "";
        int count = 0;

        for (Map.Entry<String, String> fruitPrice : fruitPrices.entrySet()) {
            if (toDouble(fruitPrice.getValue()) > highestPriceFruit && toDouble(fruitPrice.getValue()) < cashInHand) {
                highestPriceFruit = toDouble(fruitPrice.getValue());
                fruit = fruitPrice.getKey();
            }
        }

        if (!fruit.equals("")) {
            remaining = (cashInHand - currentTotal);

            if (remaining == 0) {
                combinations.setCombinations(fruitsBought);
                return;
            }

            count = (int) (remaining / highestPriceFruit);

            if (count > 0) {
                fruitPrices.remove(fruit);

                for (int i = 0; i <= (count + 1); i++) {
                    fruitsBought.put(fruit, String.valueOf(i));
                    currentTotal = calculateCurrentTotal();

                    if (currentTotal == cashInHand) {
                        combinations.setCombinations(fruitsBought);
                        fruitsBought.put(fruit, "0");
                        fruitPrices.put(fruit, String.valueOf(highestPriceFruit));
                        return;
                    } else if (i > count && currentTotal != cashInHand) {
                        fruitsBought.put(fruit, "0");
                        fruitPrices.put(fruit, String.valueOf(highestPriceFruit));
                        return;
                    } else if (!fruitPrices.isEmpty()) {
                        compute();
                    }
                }
            }
        }
    }

    public double calculateCurrentTotal() {
        double total = 0;

        if (!fruitsBought.isEmpty())
            for (Map.Entry<String, String> fruitBought : fruitsBought.entrySet())
                total += toDouble(fruitPricesBackup.get(fruitBought.getKey())) * (toDouble(fruitBought.getValue()));

        return total;
    }

    public double toDouble(String value) {
        return Double.parseDouble(value);
    }

    public void print() {
        int i = 1;

        if (combinations.getCombinations() != null) {
            System.out.println("\nFruit combinations: ");

            for (Map<String, String> map : combinations.getCombinations()) {
                System.out.print(i + ". ");
                for (Map.Entry<String, String> boughtFruit : map.entrySet()) {
                    if (!boughtFruit.getValue().equals("0")) {
                        System.out.print(boughtFruit.getValue() + " " + boughtFruit.getKey() + "(s) ");
                    }
                }
                System.out.print("\n");
                i++;
            }

            System.out.println("\n");
        } else {
            System.out.println("No combinations available!");
        }
    }
}
