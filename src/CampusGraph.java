import java.util.*;

public class CampusGraph {

    private final Map<String, List<String>> adjacencyList;

    public CampusGraph() {
        adjacencyList = new LinkedHashMap<>();
    }

    public boolean addLocation(String location) {

        if (location == null || location.isBlank()) {
            return false;
        }

        if (adjacencyList.containsKey(location)) {
            return false;
        }

        adjacencyList.put(location, new ArrayList<>());
        return true;
    }

    public boolean removeLocation(String location) {

        if (!adjacencyList.containsKey(location)) {
            return false;
        }

        adjacencyList.remove(location);

        for (List<String> neighbours : adjacencyList.values()) {
            neighbours.remove(location);
        }

        return true;
    }

    public boolean addConnection(
            String location1,
            String location2
    ) {

        if (!adjacencyList.containsKey(location1)
                || !adjacencyList.containsKey(location2)) {
            return false;
        }

        if (location1.equalsIgnoreCase(location2)) {
            return false;
        }

        if (adjacencyList.get(location1).contains(location2)) {
            return false;
        }

        adjacencyList.get(location1).add(location2);
        adjacencyList.get(location2).add(location1);

        return true;
    }

    public boolean removeConnection(
            String location1,
            String location2
    ) {

        if (!adjacencyList.containsKey(location1)
                || !adjacencyList.containsKey(location2)) {
            return false;
        }

        boolean removed1 =
                adjacencyList.get(location1).remove(location2);

        boolean removed2 =
                adjacencyList.get(location2).remove(location1);

        return removed1 || removed2;
    }

    public void displayConnections() {

        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations available.");
            return;
        }

        System.out.println("\n===== CAMPUS CONNECTIONS =====");

        for (Map.Entry<String, List<String>> entry
                : adjacencyList.entrySet()) {

            System.out.print(entry.getKey() + " -> ");

            if (entry.getValue().isEmpty()) {
                System.out.println("No connections");
            } else {
                System.out.println(
                        String.join(", ", entry.getValue())
                );
            }
        }

        System.out.println("==============================");
    }

    public void bfs(String startLocation) {

        if (!adjacencyList.containsKey(startLocation)) {
            System.out.println("Location not found.");
            return;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(startLocation);
        queue.offer(startLocation);

        System.out.println("\n===== BFS TRAVERSAL =====");

        while (!queue.isEmpty()) {

            String current = queue.poll();

            System.out.print(current + " ");

            for (String neighbour : adjacencyList.get(current)) {

                if (!visited.contains(neighbour)) {

                    visited.add(neighbour);
                    queue.offer(neighbour);
                }
            }
        }

        System.out.println();
        System.out.println("=========================");
    }
}