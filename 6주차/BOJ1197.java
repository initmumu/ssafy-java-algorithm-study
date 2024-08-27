import java.io.*;
import java.util.*;

public class BOJ1197 {
    // 간선 저장을 위한 클래스
    static class Edge implements Comparable<Edge>{
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        // 간선을 오름차순으로 정렬
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 인접 리스트를 사용하여 그래프를 구성
        List<Edge>[] graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        // 프림 알고리즘
        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        int result = 0;
        pq.add(new Edge(1, 0));
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int u = edge.to;
            int weight = edge.weight;

            // 이미 연결된 정점이면 continue
            if (visited[u]) continue;

            visited[u] = true;
            result += weight;

            for (Edge nextEdge : graph[u]) {
                if (!visited[nextEdge.to]) {
                    pq.add(nextEdge);
                }
            }
        }

        System.out.println(result);
    }
}
