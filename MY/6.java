// Program for shortest path using Bellman-Ford algorithm
import java.util.Arrays;

class BellmanFord {
    static class Edge {
        int source, destination, weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static void bellmanFord(Edge[] edges, int vertices, int source) {
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            for (Edge edge : edges) {
                if (distances[edge.source] != Integer.MAX_VALUE &&
                    distances[edge.source] + edge.weight < distances[edge.destination]) {
                    distances[edge.destination] = distances[edge.source] + edge.weight;
                }
            }
        }

        for (Edge edge : edges) {
            if (distances[edge.source] != Integer.MAX_VALUE &&
                distances[edge.source] + edge.weight < distances[edge.destination]) {
                System.out.println("Negative weight cycle detected.");
                return;
            }
        }

        System.out.println("Shortest distances: " + Arrays.toString(distances));
    }

    public static void main(String[] args) {
        int vertices = 5;
        Edge[] edges = {
            new Edge(0, 1, 6),
            new Edge(0, 2, 7),
            new Edge(1, 2, 8),
            new Edge(1, 3, 5),
            new Edge(3, 4, -3)
        };

        bellmanFord(edges, vertices, 0);
    }
}
