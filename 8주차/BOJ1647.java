import java.io.*;
import java.util.*;

public class BOJ1647 {

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

    static int N, M;
    static ArrayList<Edge>[] edges;
    static ArrayList<Integer> edgesMST = new ArrayList();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges[A].add(new Edge(B, C));
            edges[B].add(new Edge(A, C));
        }

        Prim();
        Collections.sort(edgesMST);

        int result = 0;
        for (int i = 0; i < N - 1; i++) {
            result += edgesMST.get(i);
        }

        System.out.println(result);
    }

    static void Prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];

        pq.add(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int u = edge.to;
            int w = edge.weight;

            if (visited[u]) {
                continue;
            }

            visited[u] = true;
            edgesMST.add(w);

            for (Edge e : edges[u]) {
                if (!visited[e.to]) {
                    pq.add(e);
                }
            }
        }
    }
}
