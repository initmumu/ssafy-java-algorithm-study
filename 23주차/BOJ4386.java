import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ4386 {

    // 별의 좌표를 저장하는 클래스
    static class Star {
        double x, y;

        Star(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int to;
        double distance;

        public Edge(int to, double distance) {
            this.to = to;
            this.distance = distance;
        }

        public int compareTo(Edge o) {
            return Double.compare(this.distance, o.distance);
        }
    }

    static int n;
    static List<Edge>[] graph;
    static double totalDistance = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        List<Star> stars = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] coordinate = br.readLine().split(" ");
            double x = Double.parseDouble(coordinate[0]);
            double y = Double.parseDouble(coordinate[1]);
            stars.add(new Star(x, y));
        }

        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double distance = getDistance(stars.get(i), stars.get(j));
                graph[i].add(new Edge(j, distance));
                graph[j].add(new Edge(i, distance));
            }
        }

        Prim();

        System.out.println(String.format("%.2f", totalDistance));
    }

    static void Prim() {
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue();

        visited[0] = true;
        for (Edge edge : graph[0]) {
            pq.add(edge);
        }

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int to = edge.to;
            double distance = edge.distance;

            if (visited[to]) {
                continue;
            }

            visited[to] = true;
            totalDistance += distance;

            for (Edge next : graph[to]) {
                if (!visited[next.to]) {
                    pq.add(next);
                }
            }
        }
    }

    static double getDistance(Star a, Star b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
}
