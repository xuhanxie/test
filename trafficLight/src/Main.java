import java.util.*;
class Light {
    int x;
    int y;
    boolean isGreen = true;
    public Light(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Car {
    int x;
    int y;
    // 0 => increasing y, 1 => increasing x, 2 => decreasing y, 3 => decreasing x
    int direction;
    public Car(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}

class Road {
    Integer x;
    Integer y;
    public Road(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
    public boolean isVertical() {
        return y == null;
    }
}

class Map {
    List<Road> roads;
    List<Light> lights;
    int[] cityLimitsMin;
    int[] cityLimitsMax;

    public Map(List<Road> roads, int[] cityLimitsMin, int[] cityLimitsMax) {
        lights = new ArrayList<>();
        for (Road r1 : roads) {
            for (Road r2 : roads) {
                if (r1.isVertical() && !r2.isVertical()) {
                    lights.add(new Light(r1.x, r2.y));
                }
            }
        }
        this.roads = roads;
        this.cityLimitsMin = cityLimitsMin;
        this.cityLimitsMax = cityLimitsMax;
    }

    public int simulateCar(Car car) {
        int timeCount = 0;
        while ((cityLimitsMin[0] <= car.x && car.x <= cityLimitsMax[0]) && (cityLimitsMin[1] <= car.y && car.y <= cityLimitsMax[1])) {
            timeCount++;
            boolean carAtRed = false;
            for (Light l : lights) {
                if (l.x == car.x && l.y == car.y && !l.isGreen) {
                    carAtRed = true;
                    break;
                }
            }
            if (!carAtRed) {
                switch (car.direction) {
                    case 0:
                        car.y += 1;
                        break;
                    case 1:
                        car.x += 1;
                        break;
                    case 2:
                        car.y -= 1;
                        break;
                    case 3:
                        car.x -= 1;
                        break;
                    default:
                        break;
                }
            }
            for (Light l : lights) {
                l.isGreen = !l.isGreen;
            }
        }
        return timeCount;
    }
}


public class Main {
    public static void main(String[] args) {
        Road J = new Road(1, null);
        Road A = new Road(null, 1);
        Road B = new Road(null, 2);
        List<Road> roads = new ArrayList<>();
        roads.add(J);
        roads.add(A);
        roads.add(B);
        Map m = new Map(roads, new int[] {0, 0}, new int[] {2, 3});
        System.out.println(m.simulateCar(new Car(1, 0, 0)));
    }
}