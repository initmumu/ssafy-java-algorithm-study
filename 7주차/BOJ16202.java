import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ16202 {

    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Edge> edges = new ArrayList<>();

        for (int weight = 1; weight <= M; weight++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            edges.add(new Edge(x, y, weight));
        }

        Collections.sort(edges);

        for (int i = 0; i < K; i++) {
            parent = new int[N + 1];
            for (int j = 1; j < N + 1; j++) {
                parent[j] = j;
            }

            int result = 0;
            int count = 0;

            for (Edge edge : edges) {
                if (find(edge.u) != find(edge.v)) {
                    union(edge.u, edge.v);
                    result += edge.weight;
                    count++;

                    if (count == N - 1) {
                        break;
                    }
                }
            }
            if (count == N - 1) {
                System.out.print(result +  " ");
            }
            else {
                System.out.print(0 + " ");
            }

            edges.remove(0);
        }
    }

    public static int find(int u) {
        if (parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }

    public static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            parent[v] = u;
        }
    }
}
