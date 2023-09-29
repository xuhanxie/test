import javax.sound.sampled.Line;
import java.util.*;

public class AirlineSystem {
    List<Flight> flights;
    Map<Route, List<Flight>> directFlights = new HashMap<>();
    Map<String, List<String>> routeGraph = new HashMap<>();

    public AirlineSystem(List<Flight> flights) {
        this.flights = flights;
        initialize();
    }

    public void initialize() {
        for (Flight f : flights) {
            update(f);
        }
    }

    public void update(Flight flight) {
        Route route = flight.route;
        String departure = route.departure;
        String arrival = route.arrival;
        if (!directFlights.containsKey(route)) {
            directFlights.put(route, new ArrayList<>());
        }
        directFlights.get(route).add(flight);

        if (!routeGraph.containsKey(departure)) {
            routeGraph.put(departure, new ArrayList<>());
        }
        routeGraph.get(departure).add(arrival);
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
        update(flight);
    }

    public List<Flight> getDirectFlights(Route route) {
        if (directFlights.containsKey(route)) {
            return directFlights.get(route);
        }
        return null;
    }


    public List<List<Flight>> getIndirectFlights(Route route) {
        String departure = route.departure;
        String arrival = route.arrival;
        HashSet<String> visited = new HashSet<>();
        List<List<Flight>> res = new ArrayList<>();
        List<Flight> curr = new ArrayList<>();
        dfs(departure, arrival, visited, res, curr);
        return res;
    }

    private void dfs(String start, String end, HashSet<String> visited, List<List<Flight>> res, List<Flight> curr) {
        if (start.equals(end)) {
            res.add(new ArrayList<>(curr));
            return;
        }
        if (!routeGraph.containsKey(start)) {
            return;
        }
        visited.add(start);
        for (String neighbor : routeGraph.get(start)) {
            if (visited.add(neighbor)) {
                Route route = new Route(start, neighbor);
                for (Flight f : directFlights.get(route)) {
                    curr.add(f);
                    dfs(neighbor, end, visited, res, curr);
                    curr.remove(curr.size()-1);
                }
            }
        }
    }
}
