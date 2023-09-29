import java.util.*;
public class Discount {
    private Map<Color, Double> discount = new HashMap<Color, Double>();
    public Discount() {
        for (Color c : Color.values()) {
            discount.put(c, 1.0);
        }
    }

    public Double getDiscount(Color c) {
        return discount.get(c);
    }

    public void adjustDiscount(Color c, Double dct) {
        discount.put(c, dct);
    }
}
