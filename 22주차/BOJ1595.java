import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1595 {

    static class Edge {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static List<Edge>[] tree = new ArrayList[10001];
    static boolean[] visited = new boolean[10001];
    static int maxDistance = 0;
    static int farthestCity = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i <= 10000; i++) {
            tree[i] = new ArrayList<>();
        }

        while (true) {
            try {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                tree[u].add(new Edge(v, w));
                tree[v].add(new Edge(u, w));
            }
            catch (Exception e) {
                break;
            }
        }

        // 1. 1번 도시에서부터 가장 먼 도시를 찾음
        DFS(1, 0);

        visited = new boolean[10001];

        // 2. 1에서 찾은 도시에서 가장 먼 도시를 찾음
        DFS(farthestCity, 0);

        System.out.println(maxDistance);
    }

    static void DFS(int node, int distance) {
        visited[node] = true;

        if (distance > maxDistance) {
            maxDistance = distance;
            farthestCity = node;
        }

        for (Edge edge : tree[node]) {
            if (!visited[edge.node]) {
                DFS(edge.node, distance + edge.weight);
            }
        }
    }
}
