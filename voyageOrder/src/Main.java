import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
class Order {
    String ID;
    String origin;
    String destination;
    Date dropOffDate;
    Date deliveryDate;
    Double load;
    public Order(String ID, String origin, String destination, Date dropOffDate, Date deliveryDate, Double load) {
        this.ID = ID;
        this.origin = origin;
        this.destination = destination;
        this.dropOffDate = dropOffDate;
        this.deliveryDate = deliveryDate;
        this.load = load;
    }
}

class Voyage {
    String ID;
    String origin;
    String destination;
    Date departure;
    Date arrival;
    List<Order> orders;
    Double capacity;
    Double currLoad;

    public Voyage(String ID, String origin, String destination, Date departure, Date arrival, Double capacity) {
        this.ID = ID;
        orders = new ArrayList<>();
        currLoad = 0.0;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
        this.capacity = capacity;
    }

    public void load(Order o) {
        orders.add(o);
        currLoad += o.load;
    }

    public void unLoad(Order o) {
        orders.remove(o);
        currLoad -= o.load;
    }

    public boolean canLoad(Order o) {
        return o.origin.equals(this.origin) && o.destination.equals(this.destination) && DateUtils.isEarlier(o.dropOffDate, this.departure) && DateUtils.isEarlier(this.arrival, o.deliveryDate)
                && (o.load + currLoad <= capacity);
    }
}

class DateUtils {
    public static boolean isEarlier(Date d1, Date d2) {
        return d1.compareTo(d2) <= 0;
    }
}
class OrderSystem {
    List<Voyage> voyageList;
    List<Order> orderList;

    public OrderSystem(List<Voyage> voyageList, List<Order> orderList) {
        this.voyageList = voyageList;
        this.orderList = orderList;
        Collections.sort(voyageList, new Comparator<Voyage>() {
            @Override
            public int compare(Voyage v1, Voyage v2) {
                return v1.departure.compareTo(v2.departure);
            }
        });
    }

    public void addVoyage(Voyage v) {
        voyageList.add(v);
    }

    public void addOrder(Order o) {
        orderList.add(o);
    }

    public boolean bookOrder(Order o) {
        int leftBound = binarySearch(o.dropOffDate, voyageList);

        for (; leftBound < voyageList.size(); leftBound++) {
            Voyage v = voyageList.get(leftBound);
            if (v.canLoad(o)) {
                v.load(o);
                System.out.println(v.departure);
                return true;
            }
        }
        return false;
    }

    private int binarySearch(Date target, List<Voyage> list) {
        int left = 0, right = list.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (DateUtils.isEarlier(target, list.get(mid).departure)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public List<Voyage> getFullVoyage(Order o) {
        List<Voyage> res = new ArrayList<>();
        int ptr = binarySearch(o.dropOffDate, voyageList);
        for (; ptr < voyageList.size(); ptr++) {
            Voyage v = voyageList.get(ptr);
            if (DateUtils.isEarlier(o.dropOffDate, v.departure) && DateUtils.isEarlier(v.arrival, o.deliveryDate)) {
                res.add(v);
            }
        }
        return res;
    }

    public void swap(Order order) {
        if (bookOrder(order)) {
            return;
        } else {
            List<Voyage> vList = getFullVoyage(order);
            for (Voyage v1 : vList) {
                for (Order o : v1.orders) {
                    // may can swap
                    if (v1.capacity - o.load + order.load <= v1.capacity) {
                        for (Voyage v2 : voyageList) {
                            if (v1 == v2) {
                                continue;
                            }
                            if (v2.canLoad(o)) {
                                v1.unLoad(o);
                                v1.load(order);
                                v2.load(o);
                                System.out.println(v1.orders.get(0).ID);
                                System.out.println(v2.orders.get(0).ID);
                            }
                        }
                    }
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws ParseException {
//        String dateFormat = "yyyy-MM-dd";
//        Date date= new SimpleDateFormat(dateFormat).parse("2022-8-9");

        Order order1 = new Order("1","SZ", "LA", new Date(2022, 8,9), new Date(2022,8,30), 2.0);
        Order order2 = new Order("2", "SZ", "LA", new Date(2022, 8,9), new Date(2022,8,20), 2.0);
        Voyage voyage1 = new Voyage("v1","SZ", "LA", new Date(2022, 8,9), new Date(2022,8,20), 2.0);
        Voyage voyage2 = new Voyage("v2","SZ", "LA", new Date(2022, 8,10), new Date(2022,8,21), 2.0);
        OrderSystem sys = new OrderSystem(Arrays.asList(voyage1, voyage2), Arrays.asList(order1, order2));
        sys.bookOrder(sys.orderList.get(0));
        // cannot load, full capacity
        sys.bookOrder(sys.orderList.get(1));
        // swap successfully
        sys.swap(sys.orderList.get(1));
    }
}