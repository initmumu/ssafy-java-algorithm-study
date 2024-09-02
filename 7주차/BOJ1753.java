import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753 {

    static int V;
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<Edge>[] graph;
    static int[] distance;

    static class Edge implements Comparable<Edge> {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
        }

        distance = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            distance[i] = INF;
        }

        Dijkstra(K);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(distance[i] == INF ? "INF" : distance[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void Dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int u = current.to;
            int currentDist = current.weight;

            if (currentDist > distance[u]) {
                continue;
            }

            for (Edge next : graph[u]) {
                int v = next.to;
                int weight = next.weight;

                if (distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    pq.add(new Edge(v, distance[v]));
                }
            }
        }
    }
}
