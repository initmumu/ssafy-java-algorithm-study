import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[] items;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        N = read();
        M = read();
        R = read();

        items = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            items[i] = read();
        }

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < R; i++) {
            int a = read();
            int b = read();
            int l = read();
            graph[a].add(new Node(b, l));
            graph[b].add(new Node(a, l));
        }

        int maxItems = 0;

        for (int i = 1; i <= N; i++) {
            int totalItems = dijkstra(i);
            maxItems = Math.max(maxItems, totalItems);
        }

        System.out.println(maxItems);
    }

    static int dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        distances[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentNode = current.to;
            int currentDistance = current.weight;

            if (currentDistance > distances[currentNode]) {
                continue;
            }

            for (Node neighbor : graph[currentNode]) {
                int distance = currentDistance + neighbor.weight;
                if (distance < distances[neighbor.to] && distance <= M) {
                    distances[neighbor.to] = distance;
                    pq.offer(new Node(neighbor.to, distance));
                }
            }
        }

        int totalItems = 0;
        for (int i = 1; i <= N; i++) {
            if (distances[i] <= M) {
                totalItems += items[i];
            }
        }

        return totalItems;
    }

    static class Node implements Comparable<Node> {
        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    
    public static int read() throws IOException {
    	int c, t = 0;
    	while((c=System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
    	return t;
    }
   
}
