import java.io.*;
import java.util.*;

public class BOJ1967 {

    static class Edge {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static int n;
    static List<Edge>[] tree;
    static boolean[] visited;
    static int maxDistance = 0;
    static int farthestNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tree[u].add(new Edge(v, w));
            tree[v].add(new Edge(u, w));
        }

        // 1. 루트노드에서부터 가장 먼 노드를 찾음
        DFS(1, 0);

        visited = new boolean[n + 1];

        // 2. 1에서 찾은 노드에서 가장 먼 노드를 찾음
        DFS(farthestNode, 0);

        System.out.println(maxDistance);
    }

    static void DFS(int node, int distance) {
        visited[node] = true;

        if (distance > maxDistance) {
            maxDistance = distance;
            farthestNode = node;
        }

        for (Edge edge : tree[node]) {
            if (!visited[edge.node]) {
                DFS(edge.node, distance + edge.weight);
            }
        }
    }
}
