import java.util.*;

public class Main {
    public static void main(String[] args) {
        Route chiLA = new Route("Chi", "LA");
        Route chiNY = new Route("Chi", "NY");
        Route chiDC = new Route("Chi", "DC");
        Route LASeattle = new Route("LA", "Seattle");
        Flight f1 = new Flight("1", chiLA);
        Flight f2 = new Flight("2", chiNY);
        Flight f3 = new Flight("3", chiDC);
        Flight f4 = new Flight("4", LASeattle);
        List<Flight> flights = new ArrayList<>();
        flights.add(f1);
        flights.add(f2);
        flights.add(f3);
        flights.add(f4);
        AirlineSystem sys = new AirlineSystem(flights);
        System.out.println(sys.directFlights);
        System.out.println(sys.routeGraph);
        Route test1 = new Route("Chi", "LA");
        Route test2 = new Route("Chi", "Seattle");
        System.out.println(sys.getDirectFlights(test1));
        System.out.println(sys.getIndirectFlights(test2));
    }
}