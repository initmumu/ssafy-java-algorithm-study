import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class BOJ2887 {
    
    public static class Edge implements Comparable<Edge> {
        int start, end, cost;

        Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return this.cost - other.cost;
        }
    }
    
    public static int[] parent;

    public static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if (a == b) return false;
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] planets = new int[N][4];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            planets[i][0] = Integer.parseInt(st.nextToken());
            planets[i][1] = Integer.parseInt(st.nextToken());
            planets[i][2] = Integer.parseInt(st.nextToken());
            planets[i][3] = i;
        }

        List<Edge> edges = new ArrayList<>();

        for (int d = 0; d < 3; d++) {
        	int dimension = d;
            Arrays.sort(planets, Comparator.comparingInt(p -> p[dimension]));
            for (int i = 0; i < N - 1; i++) {
                int cost = Math.abs(planets[i + 1][dimension] - planets[i][dimension]);
                edges.add(new Edge(planets[i][3], planets[i + 1][3], cost));
            }
        }

        Collections.sort(edges);

        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int minCost = 0;
        int count = 0;

        for (Edge edge : edges) {
            if (union(edge.start, edge.end)) {
                minCost += edge.cost;
                count++;
                if (count == N - 1) break;
            }
        }

        System.out.println(minCost);
    }

}
