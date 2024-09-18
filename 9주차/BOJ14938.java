import java.io.*;
import java.util.*;

public class BOJ14938 {

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

    static int n;
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<Edge>[] graph;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 아이템 수
        int[] items = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        // 간선 입력(양방향)
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, l));
            graph[b].add(new Edge(a, l));
        }

        int max = 0;

        for (int i = 1; i <= n; i++) {
            Dijkstra(i);
            int sum = 0;
            for (int d = 1; d <= n; d++) {
                if (distance[d] <= m) {
                    sum += items[d];
                }
            }
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }

    static void Dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        // 거리 배열 초기화
        distance = new int[n + 1];
        for (int i = 1; i <= n; i++) {
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
    }
}
