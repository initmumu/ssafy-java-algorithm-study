import java.io.*;
import java.util.*;

public class BOJ1865 {
    static final int INF = 30_000_000;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int T = read();

        while (T-- > 0) {
            int N = read();
            int M = read();
            int W = read();

            List<Edge> edges = new ArrayList<>();
            
            for (int i = 0; i < M; i++) {
                int u = read();
                int v = read();
                int cost = read();
                edges.add(new Edge(u, v, cost));
                edges.add(new Edge(v, u, cost));
            }

            for (int i = 0; i < W; i++) {
                int u = read();
                int v = read();
                int cost = read();
                edges.add(new Edge(u, v, -cost));
            }

            if (hasNegativeCycle(N, edges)) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.print(sb);
    }

    static boolean hasNegativeCycle(int N, List<Edge> edges) {

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        
        for (int i = 1; i < N; i++) {
            for (Edge edge : edges) {
                if (dist[edge.from] + edge.cost < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        }

        for (Edge edge : edges) {
            if (dist[edge.from] != INF && dist[edge.from] + edge.cost < dist[edge.to]) {
                return true;
            }
        }


        return false;
    }

    static class Edge {
        int from, to, cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    
	public static int read() throws IOException {
		int c, t = 0;
		while((c=System.in.read()) > 32) t = (t<<3) + (t<<1) + (c&15);
		return t;
	}
}