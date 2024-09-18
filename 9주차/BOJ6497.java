import java.io.*;
import java.util.*;

public class BOJ6497 {

    static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }
    }

    static int n, m;
    static ArrayList<Edge>[] edges;
    static int total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            if(m == 0 && n == 0) {
                break;
            }

            edges = new ArrayList[m + 1];
            for (int i = 0; i < m + 1; i++) {
                edges[i] = new ArrayList<>();
            }

            total = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                edges[x].add(new Edge(y, z));
                edges[y].add(new Edge(x, z));
                total += z;
            }

            Prim();
            System.out.println(total);
        }
    }

    static void Prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[m + 1];

        pq.add(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int u = edge.to;
            int w = edge.weight;

            if (visited[u]) {
                continue;
            }

            visited[u] = true;
            total -= w;

            for (Edge e : edges[u]) {
                if (!visited[e.to]) {
                    pq.add(e);
                }
            }
        }
    }
}
