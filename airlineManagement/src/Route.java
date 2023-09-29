import java.util.Objects;

public class Route {
    String departure;
    String arrival;
    public Route(String departure, String arrival) {
        this.departure = departure;
        this.arrival = arrival;
    }

    @Override
    public int hashCode() {
        return departure.hashCode() + arrival.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Route route = (Route) obj;
        return Objects.equals(departure, route.departure) && Objects.equals(arrival, route.arrival);
    }
}
