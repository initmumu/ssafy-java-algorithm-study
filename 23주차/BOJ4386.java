import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ4386 {

    static class Edge implements Comparable<Edge> {

        int star1;
        int star2;
        double cost;

        public Edge(int star1, int star2, double cost) {
            this.star1 = star1;
            this.star2 = star2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return Double.compare(this.cost, other.cost);
        }
    }

    static int[] parent;

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        double[][] stars = new double[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double distance = Math.sqrt(
                    Math.pow(stars[i][0] - stars[j][0], 2) +
                        Math.pow(stars[i][1] - stars[j][1], 2)
                );
                edges.add(new Edge(i, j, distance));
            }
        }

        Collections.sort(edges);

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        double totalCost = 0.0;
        for (Edge edge : edges) {
            if (find(edge.star1) != find(edge.star2)) {
                union(edge.star1, edge.star2);
                totalCost += edge.cost;
            }
        }

        System.out.printf("%.2f\n", totalCost);
    }
}
