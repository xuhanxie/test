import java.util.*;
public class Money {
    private Map<Color, Double> map = new HashMap<>();


    public Money(double red, double blue, double yellow, double green, double black, double gold) {
        map.put(Color.red, red);
        map.put(Color.blue, blue);
        map.put(Color.yellow, yellow);
        map.put(Color.green, green);
        map.put(Color.black, black);
        map.put(Color.gold, gold);
    }

    public Double getToken(Color c) {
        if (!map.containsKey(c)) {
            return null;
        }
        return map.get(c);
    }

    public boolean canCover(Money o, Discount discount) {
        Double goldTokens = map.get(Color.gold);
        for (Color c : map.keySet()) {
            Double tokenHad = getToken(c);
            Double tokenNeed = o.getToken(c) * discount.getDiscount(c);
            if (tokenHad < tokenNeed) {
                Double diff = tokenNeed - tokenHad;
                if (diff <= goldTokens) {
                    goldTokens -= diff;
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    public boolean cover(Money o, Discount discount) {
        if (!canCover(o, discount)) {
            return false;
        } else {
            for (Color c : map.keySet()) {
                Double tokenHad = getToken(c);
                Double tokenNeed = o.getToken(c) * discount.getDiscount(c);
                Double diff = tokenHad - tokenNeed;
                if (diff < 0) {
                    map.put(Color.gold, map.get(Color.gold) + diff);
                    map.put(c, 0.0);
                    continue;
                }
                map.put(c, diff);
            }
            return true;
        }
    }

    public void printStatus() {
        for (Color c : map.keySet()) {
            System.out.println("Color " + c + " with " + map.get(c) + " tokens");
        }
    }


}
