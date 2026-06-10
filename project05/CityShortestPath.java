package project05;

import java.util.*;

class Edge{
    String destination;
    int distance;

    public Edge (String destination, int distance) {
        this.destination = destination;
        this.distance = distance;
    }
}

class Node{
    String City;
    int distance;
    public Node (String City, int distance) {
        this.City = City;
        this.distance = distance;
    }
}

public class CityShortestPath {
    private Map<String, List<Edge>> graph;

    public CityShortestPath() {
        this.graph = new HashMap<>();
    }

    public void addRoad(String source, String destination, int distance){
        graph.putIfAbsent(source, new ArrayList<>());

        graph.putIfAbsent(destination, new ArrayList<>());

        graph.get(source).add(new Edge(destination, distance));

        graph.get(destination).add(new Edge(source, distance));
    }

    // Dijkstra's algorithm
    public void findShortestPath(String start, String distination){
        Map<String, Integer> distances = new HashMap<>();

        Map<String, String> previous = new HashMap<>();

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));

        for(String city : graph.keySet()) {
            distances.put(city, Integer.MAX_VALUE);
            previous.put(city, null);
        }

        distances.put(start, 0);
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node Current = pq.poll();
            String currentCity = Current.City;
            int currentDistance = Current.distance;

            for (Edge edge : graph.get(currentCity)){
                int newDistance = currentDistance + edge.distance;

                if (newDistance < distances.get(edge.destination)){
                    distances.put(edge.destination, newDistance);
                    previous.put(edge.destination, currentCity);

                    pq.add(new Node(edge.destination, newDistance));
                }
            }
        }

        if (distances.get(distination) == Integer.MAX_VALUE) {
            System.out.println("No path from " + start + " to " + distination);
            return;
        }

        List<String> path = new ArrayList<>();
        String currentCity = distination;

        while(currentCity != null){
            path.add(currentCity);
            currentCity = previous.get(currentCity);
        }
        Collections.reverse(path);

        System.out.println("\nShortest Distance :" + distances.get(distination));

        System.out.println("Shortest Path : " );

        for(int i = 0; i < path.size(); i++){
            System.out.print(path.get(i));

            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CityShortestPath cityGraph = new CityShortestPath();

        cityGraph.addRoad("Home", "Market", 5);
        cityGraph.addRoad("Home", "College", 10);
        cityGraph.addRoad("Home", "Office", 20);
        cityGraph.addRoad("Market", "Office", 2);
        cityGraph.addRoad("College", "Hospital", 1);
        cityGraph.addRoad("Office", "Hospital", 1 );

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the Source Location: ");
        String source = scanner.nextLine();

        System.out.print("Enter the Destination Location: ");
        String destination = scanner.nextLine();

        cityGraph.findShortestPath(source, destination);

        scanner.close();
    }
}

