import java.io.*;
import java.util.*;

public class BOJ1504 {

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

    static int N, E;
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<Edge>[] graph;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 입력(양방향)
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1 -> v1 -> v2 -> N 경로
        int d1 = Dijkstra(1, v1);
        int d2 = Dijkstra(v1, v2);
        int d3 = Dijkstra(v2, N);

        int result = (d1 == INF || d2== INF || d3 == INF) ? INF : d1 + d2 + d3;

        // 1 -> v2 -> v1 -> N 경로
        d1 = Dijkstra(1, v2);
        d2 = Dijkstra(v2, v1);
        d3 = Dijkstra(v1, N);

        result = Math.min(result, (d1 == INF || d2== INF || d3 == INF) ? INF : d1 + d2 + d3);

        System.out.println(result == INF ? -1 : result);

    }

    static int Dijkstra(int start, int end) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        // 거리 배열 초기화
        distance = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            distance[i] = INF;
        }

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
                    pq.add(new Edge(v, weight));
                }
            }
        }

        return distance[end];
    }
}
