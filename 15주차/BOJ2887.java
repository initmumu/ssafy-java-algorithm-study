import java.io.*;
import java.util.*;

public class BOJ2887 {

    static class Edge implements Comparable<Edge> {
        int w;     // 연결할 행성
        int cost;  // 터널 연결 비용

        public Edge(int w, int cost) {
            this.w = w;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);	// 비용에 따라 정렬
        }
    }
    
    static int N;
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int[] x = new int[N];
        int[] y = new int[N];
        int[] z = new int[N];

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
            z[i] = Integer.parseInt(st.nextToken());
        }
        
        Integer[] idx = new Integer[N];
        for (int i = 0; i < N; i++) {
            idx[i] = i;
        }

        // x 좌표에 따라 인덱스 정렬, 인접 정점 간선 추가
        Arrays.sort(idx, Comparator.comparingInt(i -> x[i]));
        for (int i = 0; i < N - 1; i++) {
            int u = idx[i]; 	// 현재 정점
            int v = idx[i + 1]; // 다음 정점
            int cost = Math.abs(x[u] - x[v]);
            graph[u].add(new Edge(v, cost)); // 양방향 간선 추가
            graph[v].add(new Edge(u, cost));
        }

        // y 좌표에 대해 간선 추가
        Arrays.sort(idx, Comparator.comparingInt(i -> y[i]));
        for (int i = 0; i < N - 1; i++) {
            int u = idx[i];
            int v = idx[i + 1];
            int cost = Math.abs(y[u] - y[v]);
            graph[u].add(new Edge(v, cost));
            graph[v].add(new Edge(u, cost));
        }

        // z 좌표에 대해 간선 추가
        Arrays.sort(idx, Comparator.comparingInt(i -> z[i]));
        for (int i = 0; i < N - 1; i++) {
            int u = idx[i];
            int v = idx[i + 1];
            int cost = Math.abs(z[u] - z[v]);
            graph[u].add(new Edge(v, cost));
            graph[v].add(new Edge(u, cost));
        }

        int minCost = Prim();
        System.out.println(minCost);
    }

    static int Prim() {
        boolean[] visited = new boolean[N];
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(0, 0));

        int totalCost = 0;
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int v = edge.w;
            int cost = edge.cost;

            if (visited[v]) {
                continue;
            }

            visited[v] = true;
            totalCost += cost;

            for (Edge e : graph[v]) {
                if (!visited[e.w]) {
                    queue.add(e);
                }
            }
        }

        return totalCost;
    }
}
