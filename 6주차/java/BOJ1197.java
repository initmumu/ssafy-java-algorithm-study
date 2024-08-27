import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

public class Main {
    static int[] parent;

    public static int find(int n) {
        if (n != parent[n]) {
            parent[n] = find(parent[n]);
        }
        return parent[n];
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return false;

        if(x <= y) parent[y] = x;
        else parent[x] = y;
        return true;
    }

    public static void main(String[] args) throws IOException {
        int V = readInt();
        int E = readInt();

        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            int u = readInt();
            int v = readInt();
            int weight = readInt();
            edges.add(new Edge(u, v, weight));
        }

        Collections.sort(edges);

        int mstWeight = 0;
        for (Edge edge : edges) {
            int u = edge.src;
            int v = edge.dest;

            if (find(u) != find(v)) {
                union(u, v);
                mstWeight += edge.weight;
            }
        }

        System.out.println(mstWeight);
    }

    public static int readInt() throws IOException {
        int c, t = 0;
        while((c = System.in.read()) > 32) t = (t<<3) + (t<<1) + (c&15);
        return t;
    }
}
