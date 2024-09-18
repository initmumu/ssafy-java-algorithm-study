import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    static boolean[] visited;
    static ArrayList<Edge>[] edges;

    public static class Edge implements Comparable<Edge> {
        public int to;
        public int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            if (M == 0 && N == 0) break;

            edges = new ArrayList[M];
            visited = new boolean[M];
            for (int i = 0; i < M; i++) {
                edges[i] = new ArrayList<>();
            }

            int max = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                max += cost;

                edges[a].add(new Edge(b, cost));
                edges[b].add(new Edge(a, cost));
            }

            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.add(new Edge(0, 0));
            int result = 0;
            int edgeCount = 0;

            while (!pq.isEmpty() && edgeCount < M) {
                Edge current = pq.poll();

                if (visited[current.to]) continue;
                visited[current.to] = true;
                result += current.cost;
                edgeCount++;

                for (Edge next : edges[current.to]) {
                    if (!visited[next.to]) {
                        pq.add(next);
                    }
                }
            }

            System.out.println(max - result);
        }
    }
}
