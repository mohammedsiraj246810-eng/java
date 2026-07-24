import java.util.*;

class UndergroundSystem {

    // Stores passenger check-in info
    private Map<Integer, CheckIn> checkInMap;

    // Stores route statistics
    private Map<String, Route> travelMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        travelMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckIn checkIn = checkInMap.get(id);

        String key = checkIn.station + "->" + stationName;
        int travelTime = t - checkIn.time;

        Route route = travelMap.getOrDefault(key, new Route());
        route.totalTime += travelTime;
        route.tripCount++;
        travelMap.put(key, route);

        checkInMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "->" + endStation;
        Route route = travelMap.get(key);
        return (double) route.totalTime / route.tripCount;
    }

    // Helper class for check-in details
    class CheckIn {
        String station;
        int time;

        CheckIn(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    // Helper class for route statistics
    class Route {
        int totalTime;
        int tripCount;

        Route() {
            totalTime = 0;
            tripCount = 0;
        }
    }
}